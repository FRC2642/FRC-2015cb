package org.team2642.robot.commands.Autonomous;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDist extends Command {

    private double magnitude;
    private double time;
    private double angle;
    private int count;
	
	public AutoDriveDist(double mag, double ang, double tim) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	magnitude = mag;
    	angle = ang;
    	time = tim;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(magnitude, 0, 0);
    	SmartDashboard.putNumber("Distance Traveled Cmd", Robot.driveTrain.getEncoderDistance());
    	Robot.driveTrain.driveAngle(magnitude, angle);
    	count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return (Robot.driveTrain.getEncoderDistance() > distance);
        return (count > (50 * time));
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
