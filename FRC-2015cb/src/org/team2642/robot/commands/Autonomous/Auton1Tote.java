package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;
import org.team2642.robot.commands.DriveTrain.*;
import org.team2642.robot.commands.Lift.*;
import org.team2642.robot.commands.Pickers.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton1Tote extends CommandGroup {
    
    public  Auton1Tote() {
    	
    	requires(Robot.Lift);
    	requires(Robot.Pickers);
    	requires(Robot.driveTrain);
    	
    	addSequential(new DriveToTote()); // 1st tote
    	//addParallel(new AutoDriveDist(0.7, 0, 2));
    	//addSequential(new SwagTheRC());
    	addSequential(new TurnDegrees(10, true));
    	addParallel(new PickersOut(0.5));
    	//addParallel(new AutoSetTote());
    	addSequential(new TurnDegrees(90, true)); //face other side of field
    	addSequential(new DriveForward(90)); //addSequential(new DriveForwardPID(90));
    	//addParallel(new DriveForward(-10)); //addParallel(new DriveForwardPID(-10));
    	addSequential(new AutoReleaseStack()); //release stack
    	
    }
}
