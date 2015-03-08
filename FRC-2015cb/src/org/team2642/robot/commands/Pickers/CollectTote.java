package org.team2642.robot.commands.Pickers;

import org.team2642.robot.Robot;
import org.team2642.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectTote extends Command {

    public CollectTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Pickers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Pickers.pickerSet(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.toteInRobot.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Pickers.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
