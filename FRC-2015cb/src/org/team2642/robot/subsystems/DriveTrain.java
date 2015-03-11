package org.team2642.robot.subsystems;

import org.team2642.robot.PIDSpeedController;
import org.team2642.robot.RobotMap;
import org.team2642.robot.commands.DriveTrain.MecanumDefault;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {
    
    Encoder leftEncoder = RobotMap.backLeftEncoder;
    Encoder rightEncoder = RobotMap.backRightEncoder;
    
	SpeedController frontLeftMotor = RobotMap.driveTrainFrontLeftMotor;
    SpeedController backLeftMotor = RobotMap.driveTrainRearLeftMotor;
    SpeedController frontRightMotor = RobotMap.driveTrainFrontRightMotor;
    SpeedController backRightMotor = RobotMap.driveTrainRearRightMotor;

    PIDSpeedController backLeftPID = new PIDSpeedController(leftEncoder, backLeftMotor, "Drive", "Back Left");
    PIDSpeedController backRightPID = new PIDSpeedController(rightEncoder, backRightMotor, "Drive", "Back Left");
    
    RobotDrive robotDrive = RobotMap.driveTrainrobotDrive;
    
    Accelerometer accel = RobotMap.accel;
    Gyro gyro = RobotMap.gyro;
    
    public String subsystemName;
    public static final double driveKp = 0.04;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static final double DistanceNormal = 91.5;
    
    public DriveTrain(String subsystem) {
    	subsystemName = subsystem;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new MecanumDefault());
        
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stopMotors() {
    	robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
    }
    
    private double getGyroOffset() {
		double gyroOffset = (getGyro() * Preferences.getInstance().getDouble("GyroStrafeConstant", .01111111111));
		// IF THE ABOSOLUTE VAL OF THE GYRO OFFSET IS LARGER THAN 1
		if (gyroOffset > 1 || gyroOffset < -1) {
			// SET THE GYRO OFFSET TO EITHER 1 OR -1
			return (-1 * (Math.abs(gyroOffset) / gyroOffset));
		} else {
			return -gyroOffset;
		}
	}
    
    public void initGyro() {
    	gyro.initGyro();
    	// set sensitivity
    }
    
    public double getGyro() {
		double angle = gyro.getAngle();
		SmartDashboard.putNumber("Gyro Angle", angle);
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
		return angle;
	}
    
    public void resetGyro() {
    	 gyro.reset();
    }
    
    public void drive(Joystick stick, double angle) {
    	robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getTwist(), angle);
    }
    
    public void drive(double speedX, double speedY, double speedR) {
    	robotDrive.mecanumDrive_Cartesian(speedX, speedY, speedR, 0);
    }
    
    public void driveStraightY(Joystick stick, double setangle) {
    	robotDrive.mecanumDrive_Cartesian(stick.getX(), (gyro.getAngle() - setangle)*-driveKp, stick.getY(), 0);
    }
    
    public void driveAngle(double angle, double speed) {
		robotDrive.mecanumDrive_Polar(speed, angle, getGyroOffset()); // You're dumb
	}
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void printEncoders() {
    	SmartDashboard.putNumber("Left Encoder Speed", leftEncoder.getRate());
    	SmartDashboard.putNumber("Right Encoder Speed", rightEncoder.getRate());
    }
    
    public double getDistanceTraveled(Encoder driveEncoder) {
		return (driveEncoder.getDistance() /* DistanceNormal*/);
	}
    
    public double getEncoderDistance() {
		return (getDistanceTraveled(rightEncoder)+ getDistanceTraveled(leftEncoder)) / 2;
	}
    
}

