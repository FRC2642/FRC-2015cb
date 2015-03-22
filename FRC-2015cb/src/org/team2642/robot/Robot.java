package org.team2642.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team2642.robot.commands.Autonomous.*;
import org.team2642.robot.commands.DriveTrain.*;
import org.team2642.robot.subsystems.*;

/**
 * 	FIRST Robotics Competition
 * 		Team 2642 Pitt Pirates
 * 			2015 Season Java code
 */
public class Robot extends IterativeRobot {

    Command autoCommand;
    SendableChooser autoChooser;

    public static OI oi;
    public static DriveTrain driveTrain;
    public static Lift Lift;
    public static Pickers Pickers;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
        driveTrain = new DriveTrain("DriveTrain");
        Lift = new Lift("Lift");
        Pickers = new Pickers("Pickers");

        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        
        // instantiate the command used for the autonomous period
        makeAutoChooser();

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	// Error next line
    	autoCommand = (Command) autoChooser.getSelected();
        autoCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoCommand != null) autoCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void makeAutoChooser() {
    	autoChooser = new SendableChooser();
        
    	autoChooser.addDefault("Do Nothing", new AutonDoNothing());
        autoChooser.addObject("Move Forward", new DriveForwardPID(100));
    	autoChooser.addObject("3 Tote Stack", new Auton3ToteStack());
    	autoChooser.addObject("2 Tote Stack", new Auton2Tote());
    	autoChooser.addObject("1 Tote", new Auton1Tote());
        
        SmartDashboard.putData("Autonomous Chooser", autoChooser);
    }
}
