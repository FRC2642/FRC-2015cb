package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;
import org.team2642.robot.commands.Lift.MoveLiftToBottom;
import org.team2642.robot.commands.Lift.MoveLiftToPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSetTote extends CommandGroup {
    
    public  AutoSetTote() {
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
    	requires(Robot.Lift);
    	
    	addSequential(new MoveLiftToPos(1100));
    	addSequential(new MoveLiftToBottom());
    	
    }
}
