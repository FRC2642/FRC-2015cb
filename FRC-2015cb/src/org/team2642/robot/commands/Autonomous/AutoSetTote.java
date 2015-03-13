package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;
import org.team2642.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSetTote extends Command {

    private boolean liftUp, liftDown;
	
	public AutoSetTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.Lift.getTopLimit() || (Robot.Lift.getBottomLimit() && !Robot.Lift.getToteInRobot())){//stop at upper limits
    		liftUp = false;
    		liftDown = false;
    	}else{
    		if(Robot.Lift.getToteInRobot() && !liftUp && !liftDown){//auto load up to dogs
    			liftUp = true; 
    			liftDown = false;
    		}else if(liftUp){ //go up to dogs
    			if(Robot.Lift.getLiftEncoderDist() > (Lift.UPPERBOUND + 20)){
    				liftUp = false;
    				liftDown = true;
    			}
    			Robot.Lift.moveLiftUp();
    		}else if(liftDown){//go down to bottom
    			if(Robot.Lift.getLiftEncoderDist() < (Lift.LOWERBOUND + 20)){
    				liftUp = false;
    				liftDown = false;
    			}
    			Robot.Lift.moveLiftDown();
    		}else{
    			Robot.Lift.stopLift();
    		}	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
