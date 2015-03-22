package org.team2642.robot.commands.DriveTrain;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	double distance;
	double setangle;
	
    public DriveForward(double inches) {
		requires(Robot.driveTrain);
		distance = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setangle = Robot.driveTrain.getGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (distance > 0) {
    		Robot.driveTrain.driveStraight(0.7, setangle);
    	}
    	else if (distance < 0) {
    		Robot.driveTrain.driveStraight(-0.7, setangle);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.distanceOnTarget(distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
