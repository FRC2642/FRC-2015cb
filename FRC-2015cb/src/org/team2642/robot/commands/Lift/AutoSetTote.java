package org.team2642.robot.commands.Lift;

import org.team2642.robot.Robot;
import org.team2642.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSetTote extends Command {

    private boolean liftUp = true;
    
	
	public AutoSetTote() {
    	requires(Robot.Lift);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.Lift.moveLiftUp();
    	if (Robot.Lift.isLiftAboveDogs() && liftUp == true)
    		liftUp = false;
    	if (!liftUp)
    		Robot.Lift.moveLiftDown();
    }

    protected boolean isFinished() {
        return (!liftUp && (Robot.Lift.getBottomLimit() || Robot.Lift.liftAtTarget(Robot.Lift.getLiftLowBound())));
    }

    protected void end() {
    	Robot.Lift.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
