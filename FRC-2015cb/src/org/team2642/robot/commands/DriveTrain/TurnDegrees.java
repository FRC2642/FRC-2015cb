package org.team2642.robot.commands.DriveTrain;

import org.team2642.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnDegrees extends Command {
    double setpoint;
    double degrees;
    
    public TurnDegrees(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        setpoint = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.turnController.setSetpoint(setpoint);
        SmartDashboard.putNumber("Gyro setpoint", setpoint);
        Robot.driveTrain.turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.turnController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}