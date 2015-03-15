package org.team2642.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

import org.team2642.robot.Robot;

/**
 *
 */
public class  ArcadeDefault extends Command {

    public ArcadeDefault() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.resetGyro();
    }

    protected void execute() {
    	Robot.driveTrain.arcadeDrive(Robot.oi.getStick());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveTrain.arcadeDrive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}