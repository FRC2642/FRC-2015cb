package org.team2642.robot.subsystems;

//import org.team2642.robot.PIDSpeedController;
import org.team2642.robot.RobotMap;
import org.team2642.robot.commands.DriveTrain.*;

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

    //PIDSpeedController backLeftPID = new PIDSpeedController(leftEncoder, backLeftMotor, "Drive", "Back Left");
    //PIDSpeedController backRightPID = new PIDSpeedController(rightEncoder, backRightMotor, "Drive", "Back Left");
    
    public PIDController straightController;
    public PIDController turnController;
    
    RobotDrive robotDrive = RobotMap.driveTrainrobotDrive;
    
    Accelerometer accel = RobotMap.accel;
    Gyro gyro = RobotMap.gyro;
    
    public String subsystemName;
    public static final double driveKp = 0.04;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static final double DistanceNormal = 91.5;
    
    private double gyrozero = 0;
    
    public DriveTrain(String subsystem) {
    	subsystemName = subsystem;
    	TurnControllerHandler turnHandler = new TurnControllerHandler();
        turnController = new PIDController(
                -Preferences.getInstance().getDouble("Turn P", 0),
                -Preferences.getInstance().getDouble("Turn I", 0),
                -Preferences.getInstance().getDouble("Turn D", 0),
                turnHandler, turnHandler);

        turnController.setAbsoluteTolerance(Preferences.getInstance().getDouble("Turn Drive PID Abs Tolerance", 0));
        turnController.setOutputRange(-0.7, 0.7);

        StraightControllerHandler straightHandler = new StraightControllerHandler();
        straightController = new PIDController(
        		Preferences.getInstance().getDouble("DriveStraight P", 0),
        		Preferences.getInstance().getDouble("DriveStraight I", 0),
        		Preferences.getInstance().getDouble("DriveStraight D", 0),
                straightHandler, straightHandler);
        straightController.setAbsoluteTolerance(Preferences.getInstance().getDouble("Straight Drive PID Abs Tolerance", 0));
        straightController.setOutputRange(-0.7, 0.7);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDefault());
        
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private class StraightControllerHandler implements PIDSource, PIDOutput {
    	@Override
    	public void pidWrite(double output) {
    		// Do something with the output PID value, like update motors
    		arcadeDrive(output, 0);
    	}
    	
    	@Override
    	public double pidGet() {
    		// Return the sensor value for the PID input
    		return getEncoderDistance();
    	}
    }

    private class TurnControllerHandler implements PIDSource, PIDOutput {
    	@Override
    	public void pidWrite(double output) {
    		// Do something with the output PID value, like update motors
    		arcadeDrive(0, output);
    	}
    	
    	@Override
    	public double pidGet() {
    		// Return the sensor value for the PID input
    		return getAbsGyro();
    	}
    }
    
    public void stopMotors() {
    	robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
    }
    
    /*private double getGyroOffset() {
		double gyroOffset = (getGyro() * Preferences.getInstance().getDouble("GyroStrafeConstant", .01111111111));
		// IF THE ABOSOLUTE VAL OF THE GYRO OFFSET IS LARGER THAN 1
		if (gyroOffset > 1 || gyroOffset < -1) {
			// SET THE GYRO OFFSET TO EITHER 1 OR -1
			return (-1 * (Math.abs(gyroOffset) / gyroOffset));
		} else {
			return -gyroOffset;
		}
	}*/
    
    public void initGyro() {
    	gyro.initGyro();
    	// set sensitivity
    }
    
    public double getGyro() {
		double angle = gyro.getAngle() - gyrozero;
		SmartDashboard.putNumber("Gyro Angle", angle);
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
		return angle;
	}
    
    public double getAbsGyro() {
    	double angle = gyro.getAngle();
		SmartDashboard.putNumber("Gyro Angle", angle);
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
		return angle;
    }
    
    public void resetGyro() {
    	gyrozero = gyro.getAngle();
    	//gyro.reset();
    }
    
    /*public void drive(Joystick stick, double angle) {
    	robotDrive.mecanumDrive_Cartesian(stick.getX()/2, stick.getTwist()/2, stick.getY()/2, angle);
    }
    
    public void drive(double speedX, double speedY, double speedR) {
    	robotDrive.mecanumDrive_Cartesian(speedX, speedY, speedR, 0);
    }
    
    public void driveFullSpeed(Joystick stick, double angle) {
    	robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getTwist(), stick.getY(), angle);
    }
    
    public void driveCrabStraight(Joystick stick, double setangle) {
    	robotDrive.mecanumDrive_Cartesian(stick.getX(), (setangle - getGyro())*driveKp, stick.getY(), 0);
    }
    
    public void driveAngle(double magnitude, double angle) {
		robotDrive.mecanumDrive_Polar(magnitude, angle, getGyroOffset());
	}*/
    
    public void arcadeDrive(Joystick stick) {
    	robotDrive.arcadeDrive(stick.getY()*0.7, stick.getX()*0.7);
    }
    
    public void arcadeDrive(double speedY, double speedR) {
    	robotDrive.arcadeDrive(speedY, speedR);
    }
    
    public void driveFullSpeed(Joystick stick) {
    	robotDrive.arcadeDrive(stick);
    }
    
    public void driveStraight(Joystick stick, double setangle) {
    	robotDrive.arcadeDrive(stick.getY(), (setangle - getGyro())*driveKp);
    }
    
    public void driveAngle(double magnitude, double angle) {
		robotDrive.arcadeDrive(0, angle);
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

