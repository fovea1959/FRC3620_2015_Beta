// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3620.GoldenCode2014.subsystems;
import org.usfirst.frc3620.GoldenCode2014.RobotMap;
import org.usfirst.frc3620.GoldenCode2014.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3620.GoldenCode2014.Robot;
import org.usfirst.frc3620.GoldenCode2014.RobotMode;
/**
 *
 */
public class SensorSubsystem extends Subsystem {
    static final public int BLUE = 3;
    static final public int COLORWIPE = 1;
    static final public int RED = 2;
    static final public int GREEN = 4;
    static final public int PURPLE = 5;
    
    boolean DEBUG = false;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalInput digitalSonar = RobotMap.sensorSubsystemDigitalSonar;
    DigitalOutput arduino1 = RobotMap.sensorSubsystemArduino1;
    DigitalOutput arduino2 = RobotMap.sensorSubsystemArduino2;
    DigitalOutput arduino4 = RobotMap.sensorSubsystemArduino4;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Counter counter = new Counter(digitalSonar);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new SensorCommand());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * add any needed code to run when robot powers up.
     */
    public void init() {
        //counter.start(); stop/start methods removed. start automatically at creation of device
        counter.setSemiPeriodMode(true);
        SmartDashboard.putNumber("light", 0);
        sendToArduino(0);
    }
    /**
     * add any needed code to run if the mode changes.
     */
    public void modeChanged() {
        if (Robot.getCurrentRobotMode() == RobotMode.DISABLED) {
            if (Robot.getPreviousRobotMode() == RobotMode.AUTONOMOUS) {
                if (DEBUG) 
                  System.out.println("SensorSubsystem: disabled and previous was autonomous");
                if (Robot.driverStation.getAlliance() == DriverStation.Alliance.Blue) {
                    Robot.sensorSubsystem.sendToArduino(SensorSubsystem.BLUE);
                } else {
                    Robot.sensorSubsystem.sendToArduino(SensorSubsystem.RED);
                }
            } else {
                Robot.sensorSubsystem.sendToArduino(COLORWIPE);
                if (DEBUG)
                    System.out.println("SensorSubsystem: disabled and previous was not autonomous");
            }
        }
    }
    /**
     * add any needed code to run everytime periodic is called.
     */
    public void periodic() {
        //SmartDashboard.putNumber("analog sonar", analogSonar.getVoltage());
        //SmartDashboard.putNumber("average analog sonar", analogSonar.getAverageVoltage());
        //SmartDashboard.putNumber("digital sonar in feet", getDigitalDistanceInFeet());
        //SmartDashboard.putNumber("digital sonar ", counter.getPeriod());
        //SmartDashboard.putNumber("feet", getAnalogDistanceInFeet());
    }
   
    public double getDigitalDistanceInFeet() {
        return (((counter.getPeriod() * 1000000) / 147) / 12);
    }
    public boolean sonarErrorTrue() {
        SmartDashboard.putBoolean("Sonar Inconsistant", true);
        return true;
    }
    public boolean sonarErrorfalse() {
        SmartDashboard.putBoolean("Sonar Inconsistant", false);
        return false;
    }
    public void sendToArduino(int i) {
        SmartDashboard.putNumber("arduino", i);
        //System.out.println(i);
        if ((i & 1) != 0) {
            arduino1.set(true);
            
        } else {
            arduino1.set(false);
        }
        if ((i & 2) != 0) {
            arduino2.set(true);
        } else {
            arduino2.set(false);
        }
        if ((i & 4) != 0) {
            arduino4.set(true);
        } else {
            arduino4.set(false);
        }
    }
}
