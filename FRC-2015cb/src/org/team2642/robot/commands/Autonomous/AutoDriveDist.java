package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDist extends Command {

    private double speed;
    private double distance;
    private double angle;
	
	public AutoDriveDist(double spd, double ang, double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	speed = spd;
    	distance = dist;
    	angle = ang;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(speed, 0, 0);
    	SmartDashboard.putNumber("Distance Traveled Cmd", Robot.driveTrain.getEncoderDistance());
    	Robot.driveTrain.driveAngle(angle, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.driveTrain.getEncoderDistance() > distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
