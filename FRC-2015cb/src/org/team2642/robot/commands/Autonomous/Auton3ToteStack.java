package org.team2642.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.team2642.robot.commands.DriveTrain.*;
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
    	
    	addSequential(new ToTote()); // 1st tote
    	//addParallel(new AutoDriveDist(0.7, 0, 2));
    	//addSequential(new SwagTheRC());
    	addSequential(new TurnDegrees(10, true));
    	addParallel(new PickersOut(0.5));
    	addParallel(new AutoSetTote());
    	addSequential(new DriveLength(40));
    	addSequential(new TurnDegrees(-10, true));
    	addSequential(new ToTote()); // 2nd tote
    	addSequential(new TurnDegrees(10, true));
    	addParallel(new PickersOut(0.5));
    	addParallel(new AutoSetTote());
    	addSequential(new DriveLength(40));
    	addSequential(new TurnDegrees(-10, true));
    	addSequential(new ToTote()); // 3rd tote
    	addSequential(new TurnDegrees(90, true));
    	addSequential(new DriveLength(90));
    	addSequential(new AutoReleaseStack());
    	addParallel(new DriveLength(-10));
    	
    	
    }
}
