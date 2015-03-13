package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2642.robot.commands.DriveTrain.*;

/**
 *
 */
public class SwagTheRC extends CommandGroup {
    
    public  SwagTheRC() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.driveTrain);
    	
    	addSequential(new AutoDriveDist(0.6, 20, 2));
    	addSequential(new AutoDriveDist(0.6, -20, 2));
    	
    	
    }
}
