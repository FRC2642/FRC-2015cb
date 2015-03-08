package org.team2642.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2642.robot.commands.Lift.*;
import org.team2642.robot.commands.Pickers.*;
import org.team2642.robot.*;

/**
 *
 */
public class AutoStack extends CommandGroup {
    
    public  AutoStack() {
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
    	//addSequential(new PickerIn);
    	requires(Robot.Lift);
    	requires(Robot.Pickers);
    	
    	addSequential(new CollectTote());
    	addParallel(new AutoDriveDist(0.6, 0, 100));
    	addSequential(new AutoSetTote());
    	addSequential(new SwagTheRC());
    	addSequential(new CollectTote());
    	addParallel(new AutoDriveDist(0.6, 0, 100));
    	addSequential(new AutoSetTote());
    	addSequential(new SwagTheRC());
    	addSequential(new CollectTote());
    	addParallel(new AutoDriveDist(0.6, 0, 100));
    	addSequential(new AutoDriveDist(0.6, 90, 500));
    	addParallel(new AutoDriveDist(0.6, 180, 200));
    	
    	
    	
    }
}
