package org.team2642.robot.subsystems;

//import org.team2642.robot.PIDSpeedController;
import org.team2642.robot.RobotMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Lift extends Subsystem {
	
    Talon lift = RobotMap.liftMotor;
    
    DigitalInput topLimit = RobotMap.topLimit;
    DigitalInput bottomLimit = RobotMap.bottomLimit;
    DigitalInput recieve = RobotMap.toteInRobot;
    DigitalInput lot = RobotMap.lotOfTotes;
    
    Encoder liftEncoder = RobotMap.liftEncoder;
    
    Compressor compressor = RobotMap.compressor;
    
    Solenoid dogs = RobotMap.dogs;
    Solenoid pusher = RobotMap.pusher;
    Solenoid flipper = RobotMap.flipper;
    Solenoid stackArm = RobotMap.thatArmThatOpensToReleaseStacks;
    
    //PIDSpeedController pid = new PIDSpeedController(liftEncoder, lift, "Lift", "Speed Control");
    
    public PIDController liftController;
    
    public String subsystemName;
    
    /*public static final double UPPERBOUND = 1150;
    public static final double LOWERBOUND = 80;*/
    
    /*public static final double UPSPEED = 0.7;
    public static final double DOWNSPEED = -0.7;*/
    
    public final double LIFTTOLERANCE = 10;
    
    /*
    public static DigitalInput topLimit;
    public static DigitalInput bottomLimit;
    public static DigitalInput toteInRobot;
    public static DigitalInput lotOfTotes;
    public static Encoder liftEncoder;
    public static Encoder backLiftEncoder;
    public static Encoder frontLiftEncoder;
	 */

    public Lift(String subsystem) {
    	subsystemName = subsystem;
    	LiftControllerHandler liftHandler = new LiftControllerHandler();
    	liftController = new PIDController(
                Preferences.getInstance().getDouble("Lift P", 0),
                Preferences.getInstance().getDouble("Lift I", 0),
                Preferences.getInstance().getDouble("Lift D", 0),
                liftHandler, liftHandler);
        liftController.setAbsoluteTolerance(Preferences.getInstance().getDouble("Turn Drive PID Abs Tolerance", 0));
        liftController.setOutputRange(-1, 1);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        //setDefaultCommand(new LiftOverride());
    }
    
    /*public void setPIDConstants() {
		double p = Preferences.getInstance().getDouble("LiftSpeedP", 0);
		double i = Preferences.getInstance().getDouble("LiftSpeedI", 0);
		double d = Preferences.getInstance().getDouble("LiftSpeedD", 0);
		double f = Preferences.getInstance().getDouble("LiftSpeedF", 0);

		pid.setConstants(p, i, d, f);

		liftEncoder.setDistancePerPulse(Preferences.getInstance().getDouble(
				subsystemName + "LiftDistPerPulse", 1));
	}*/
    
    private class LiftControllerHandler implements PIDSource, PIDOutput {
    	@Override
    	public void pidWrite(double output) {
    		// Do something with the output PID value, like update motors
    		lift.set(output);
    	}
    	
    	@Override
    	public double pidGet() {
    		// Return the sensor value for the PID input
    		return getLiftEncoderDist();
    	}
    }
    
    /*private class LiftRateControllerHandler implements PIDSource, PIDOutput {
    	@Override
    	public void pidWrite(double output) {
    		// Do something with the output PID value, like update motors
    		lift.set(output);
    	}
    	
    	@Override
    	public double pidGet() {
    		// Return the sensor value for the PID input
    		return getLiftEncoderRate();
    	}
    }*/
    
    private double getLiftSpeed() {
    	return Math.max(Preferences.getInstance().getDouble("LiftSpeed", 0), 0);
	}
    
    public double getLiftHighBound() {
    	return Math.min(Preferences.getInstance().getDouble("LiftUpBound", 0), 1200);
    }
    
    public double getLiftLowBound() {
    	return Preferences.getInstance().getDouble("LiftDownBound", 0);
    }
    
    /*public void moveLiftUpPID() {
		//movementState = LiftMovement.Up;
		//releaseBrake();
		if (!getTopLimit() || liftEncoder.getDistance() < getLiftHighBound()) {
			pid.set(-getLiftSpeed());
		}
		SmartDashboard.putString(subsystemName + "Move state", "Up");
		SmartDashboard.putNumber(subsystemName, liftEncoder.getRate());
	}*/
    
    public void moveLiftUp() {
		//movementState = LiftMovement.Up;
		//releaseBrake();
		if (!getTopLimit() || liftEncoder.getDistance() < getLiftHighBound()) {
			lift.set(getLiftSpeed());
		}
		SmartDashboard.putString(subsystemName + "Move state", "Up");
		SmartDashboard.putNumber(subsystemName, liftEncoder.getRate());
	}
    
    /*public void moveLiftDownPID() {
		//movementState = LiftMovement.Down;
		//releaseBrake();
		if (liftEncoder.getDistance() > getLiftLowBound()) {
    		pid.set(getLiftSpeed());
		}
		SmartDashboard.putString(subsystemName + "Move state", "Down");
		SmartDashboard.putNumber(subsystemName, liftEncoder.getRate());
	}*/
    
    public void moveLiftDown() {
		//movementState = LiftMovement.Down;
		//releaseBrake();
		if (liftEncoder.getDistance() > getLiftLowBound()) {
    		lift.set(-getLiftSpeed());
		}
		SmartDashboard.putString(subsystemName + "Move state", "Down");
		SmartDashboard.putNumber(subsystemName + "Lift Rate", liftEncoder.getRate());
	}

	public boolean isLiftAboveDogs() {
		return (liftEncoder.getDistance() > 1200);
	}

	public void stopLift() {
		//movementState = LiftMovement.Stopped;
		lift.set(0);
		//setBrake();
		SmartDashboard.putString(subsystemName + "Move state", "Stop");
	}
	
    public double getLiftEncoderDist() {
    	SmartDashboard.putNumber(subsystemName + "Lift Dist", liftEncoder.getDistance());
    	return liftEncoder.getDistance();
    }
    public double getLiftEncoderRate() {
    	return liftEncoder.getRate();
    }
    
    public void resetLiftEncoder() {
    	liftEncoder.reset();
    }
    
    public boolean getToteInRobot() {
    	return recieve.get();
    }
    
    public boolean getTopLimit() {
    	return topLimit.get();
    }
    
    public boolean getBottomLimit() {
    	return bottomLimit.get();
    }
    
    public boolean getFiveIn() {
    	return lot.get();
    }
    
    public boolean liftInRightPlace() {
    	return ((liftEncoder.getDistance() < getLiftHighBound()) || (liftEncoder.getDistance() > getLiftLowBound()));
    }
    
    public void moveLiftToPos(double position) {
    	if (liftEncoder.getDistance() > position + LIFTTOLERANCE) {
    		lift.set(getLiftSpeed());
    	} else if (liftEncoder.getDistance() < position - LIFTTOLERANCE) {
    		lift.set(-getLiftSpeed());
    	}
    }
    
    public boolean liftAtTarget(double position) {
    	//double tolerance = Preferences.getInstance().getDouble("Lift Tolerance", 10);
    	return (liftEncoder.getDistance() < position + LIFTTOLERANCE && liftEncoder.getDistance() > position - LIFTTOLERANCE);
    }
    
    public void toggleDogs() {
    	if (dogs.get() == true) {
    		dogs.set(false);
    	} else {
    		dogs.set(true);
    	}
    }
    public void setDogs(boolean set) {
    	dogs.set(set);
    }
    public void togglePusher() {
    	if (pusher.get() == true) {
    		pusher.set(false);
    	} else {
    		pusher.set(true);
    	}
    }
    public void setPusher(boolean set) {
    	pusher.set(set);
    }
    public void toggleFlipper() {
    	if (flipper.get() == true) {
    		flipper.set(false);
    	} else {
    		flipper.set(true);
    	}
    }
    public void setFlipper(boolean set) {
    	flipper.set(set);
    }
    public void toggleStackArm() {
    	if (stackArm.get() == true) {
    		stackArm.set(false);
    	} else {
    		stackArm.set(true);
    	}
    }
    public void setStackArm(boolean set) {
    	stackArm.set(set);
    }
    
    public void startCompressor() {
    	compressor.start();
    }
    public void stopCompressor() {
    	compressor.stop();
    }
    
    
    
}

