package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;
import org.team2642.robot.commands.DriveTrain.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDriveForward extends CommandGroup {
    
    public  AutonDriveForward() {
        
    	requires(Robot.driveTrain);
    	
    	//addSequential(new DriveForwardPID(100));
    	addSequential(new DriveForward(100));
    	
    }
}
