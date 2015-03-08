package org.team2642.robot.subsystems;

import org.team2642.robot.RobotMap;
import org.team2642.robot.commands.Pickers.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pickers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon leftPicker = RobotMap.leftPicker;
	Talon rightPicker = RobotMap.rightPicker;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand();
    }
    
    public void stopMotors() {
    	leftPicker.set(0);
    	rightPicker.set(0);
    }
    
    public void pickerSet(double speed) {
    	leftPicker.set(-speed);
    	rightPicker.set(speed);
    }
    
    
    
}

