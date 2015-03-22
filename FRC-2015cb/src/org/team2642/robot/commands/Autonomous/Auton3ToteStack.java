package org.team2642.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.team2642.robot.commands.DriveTrain.*;
import org.team2642.robot.commands.Lift.*;
import org.team2642.robot.commands.Pickers.*;
import org.team2642.robot.*;

/**
 *
 */
public class Auton3ToteStack extends CommandGroup {
    
    public  Auton3ToteStack() {
        
    	requires(Robot.Lift);
    	requires(Robot.Pickers);
    	requires(Robot.driveTrain);
    	
    	addSequential(new DriveToTote()); // 1st tote
    	//addParallel(new AutoDriveDist(0.7, 0, 2));
    	//addSequential(new SwagTheRC());
    	addSequential(new TurnDegrees(10, true));
    	addParallel(new PickersOut(0.5));
    	addParallel(new AutoSetTote());
    	addSequential(new DriveForward(40)); //addSequential(new DriveForwardPID(40));
    	addSequential(new TurnDegrees(-10, true));
    	addSequential(new DriveToTote()); // 2nd tote
    	addSequential(new TurnDegrees(10, true));
    	addParallel(new PickersOut(0.5));
    	addParallel(new AutoSetTote());
    	addSequential(new DriveForward(40)); //addSequential(new DriveForwardPID(40));
    	addSequential(new TurnDegrees(-10, true));
    	addSequential(new DriveToTote()); // 3rd tote
    	addSequential(new TurnDegrees(90, true));
    	addSequential(new DriveForward(90)); //addSequential(new DriveForwardPID(90));
    	//addParallel(new DriveForward(-10)); //addSequential(new DriveForwardPID(-10));
    	addSequential(new AutoReleaseStack());
    	
    	
    }
}
