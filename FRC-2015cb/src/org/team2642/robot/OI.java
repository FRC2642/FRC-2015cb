package org.team2642.robot;

import org.team2642.robot.commands.Autonomous.*;
import org.team2642.robot.commands.DriveTrain.*;
import org.team2642.robot.commands.Lift.*;
import org.team2642.robot.commands.Pickers.*;
import org.team2642.robot.triggers.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    public Joystick stick;
    public Joystick auxstick;
    public Joystick auxcard;


    public OI() {

        stick = new Joystick(0);
        auxstick = new Joystick(1);
        auxcard = new Joystick(2);
        
        //DriveTrain Buttons
        Button resetGyro = new JoystickButton(auxcard, 5);
        //Button toggleFieldOrient = new JoystickButton(stick, 2);
        Button stopDrive = new JoystickButton(stick, 2);
        Button fullSpeed = new JoystickButton(stick, 1);
        
        //DriveTrain Commands
        resetGyro.whenPressed(new ResetGyro());
        //toggleFieldOrient.whileHeld(new DriveStraight());
        stopDrive.whileHeld(new StopDrive());
        fullSpeed.whileHeld(new DriveArcadeFullSpeed());
        
        //Lift Buttons
        //Button flipper = new JoystickButton(stick, 3);
        Button manualOn = new JoystickButton(auxcard, 12);
        Trigger autoSetTote = new ToteInRobot();
        Button manualUp = new JoystickButton(auxstick, 3);
        Button manualDown = new JoystickButton(auxstick, 2);
        Button zeroLift = new JoystickButton(auxstick, 10);
        Button autoReleaseStack = new JoystickButton(auxstick, 1);
        
        Button dogsset = new JoystickButton(auxstick, 6);
        Button pusherset = new JoystickButton(auxstick, 7);
        
        Button setpoint1 = new JoystickButton(auxcard, 11);
        
        //Lift Commands
        //flipper.whenPressed(new FlipperToggle());
        // Manual Lift
        manualUp.whileHeld(new MoveLiftUp());
    	manualDown.whileHeld(new MoveLiftDown());
    	zeroLift.whenPressed(new ZeroLiftEncoder());
    	// Auto Lift
        if (!manualOn.get())
        	autoSetTote.whenActive(new AutoSetTote());
        
        autoReleaseStack.whenPressed(new AutoReleaseStack());
        
        dogsset.whenReleased(new DogsToggle());
        pusherset.whenReleased(new PusherToggle());
        
        setpoint1.whenPressed(new MoveLiftToPos(170));
        
        //Picker Buttons
        Button pickerin = new JoystickButton(auxcard, 9);
        Button pickerout = new JoystickButton(auxcard, 7);
        
        //Picker Methods
        pickerin.whileHeld(new PickersIn(0.5));
        pickerout.whileHeld(new PickersOut(0.5));
        
        
        // SmartDashboard Buttons
        SmartDashboard.putBoolean("Manual is On", manualOn.get());
        //Drivetrain Cmds
        SmartDashboard.putData("Drive Default", new ArcadeDefault());
        SmartDashboard.putData("Drive Full Speed", new DriveArcadeFullSpeed());
        SmartDashboard.putData("Drive Straight", new DriveStraight());
        //SmartDashboard.putData("Field Oriented Drive", new FieldOrientDrive());
        SmartDashboard.putData("Reset Gyro", new ResetGyro());
        //Lift Cmds
        SmartDashboard.putData("Move Lift To Bottom", new MoveLiftToBottom());
        SmartDashboard.putData("Move Lift To Top", new MoveLiftToTop());
        SmartDashboard.putData("Auto Intake", new AutoSetTote());
        SmartDashboard.putData("Auto Release Stack", new AutoReleaseStack());
        //Lift Aux Cmds
        SmartDashboard.putData("Dogs Toggle", new DogsToggle());
        SmartDashboard.putData("Flipper Toggle", new FlipperToggle());
        SmartDashboard.putData("Pusher Toggle", new PusherToggle());
        //Autonomous
        SmartDashboard.putData("Autonomous Tote Stack", new Auton3ToteStack());
        SmartDashboard.putData("Go Foward", new DriveDirection(500, 0));
        
        
        //Other data
        
        

    } //end OI constructor
    
    public Joystick getStick() {
        return stick;
    }
    public double getStickX() {
    	return stick.getX();
    }
    public double getStickY() {
    	return stick.getY();
    }
    public double getStickTwist() {
    	return stick.getTwist();
    }
    
    public Joystick getAuxStick() {
    	return auxstick;
    }
    public Joystick getAuxCard() {
    	return auxcard;
    }
    
    
}

