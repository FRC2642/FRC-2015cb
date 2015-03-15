package org.team2642.robot.triggers;

import org.team2642.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ToteInRobot extends Trigger {
    
    public boolean get() {
        return RobotMap.toteInRobot.get();
    }
}
