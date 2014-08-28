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
import org.usfirst.frc3620.GoldenCode2014.PreferencesNames;
import org.usfirst.frc3620.GoldenCode2014.Robot;
/**
 *
 */
public class PneumaticSubsystem extends Subsystem {
    boolean DEBUG = false;
    long hoopT0 = 0;
    long clampT0 = 0;
    
    DoubleSolenoid.Value lastClamp = DoubleSolenoid.Value.kOff;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid loaderCylinder1 = RobotMap.pneumaticSubsystemLoaderCylinder1;
    DoubleSolenoid pushCylinder = RobotMap.pneumaticSubsystemPushCylinder;
    Compressor compressor1 = RobotMap.pneumaticSubsystemCompressor1;
    DoubleSolenoid loadCylinder2 = RobotMap.pneumaticSubsystemLoadCylinder2;
    DoubleSolenoid clampCylinder = RobotMap.pneumaticSubsystemClampCylinder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public boolean getHoopDown() {
        boolean rv;
        if (hoopT0 == 0) {
            rv = false;
        } else {
            long hoopElapsedTime = System.currentTimeMillis() - hoopT0;
            if (hoopElapsedTime >= 1000) {
                
                rv = true;
            } else {
                rv = false;
            }
        }
        return rv;
    }
    public void hoopDown() {
        if (DEBUG) {
            
        }
        loaderCylinder1.set(DoubleSolenoid.Value.kForward);
        loadCylinder2.set(DoubleSolenoid.Value.kForward);
        if (hoopT0 == 0) {
            hoopT0 = System.currentTimeMillis();
        }
        Robot.getTelemetryTable().putString("pneumatics.hoop", "down");
    }
    public void hoopUp() {
        if (DEBUG) {
            
        }
        loaderCylinder1.set(DoubleSolenoid.Value.kReverse);
        loadCylinder2.set(DoubleSolenoid.Value.kReverse);
        hoopT0 = 0;
        Robot.getTelemetryTable().putString("pneumatics.hoop", "up");
    }
    public void hoopRelax() {
        if (DEBUG) {
            System.out.println("hoop Relax");
        }
        loaderCylinder1.set(DoubleSolenoid.Value.kOff);
        loadCylinder2.set(DoubleSolenoid.Value.kOff);
        Robot.getTelemetryTable().putString("pneumatics.hoop", "off");
    }
    public void pusherPushOut() {
        if (DEBUG) {
            System.out.println("Push out");
        }
        pushCylinder.set(DoubleSolenoid.Value.kForward);
        Robot.getTelemetryTable().putString("pneumatics.push", "out");
    }
    public void pusherPushIn() {
        if (DEBUG) {
            System.out.println("Push in");
        }
        pushCylinder.set(DoubleSolenoid.Value.kReverse);
        Robot.getTelemetryTable().putString("pneumatics.push", "in");
    }
    public void pusherRelax() {
        if (DEBUG) {
            System.out.println("Push off");
        }
        pushCylinder.set(DoubleSolenoid.Value.kOff);
        Robot.getTelemetryTable().putString("pneumatics.push", "off");
    }
    /**
     * add any needed code to run when robot powers up.
     */
    public void init() {
        hoopUp();
        pusherPushIn();
        updateDriverStationClampDisplay(clampCylinder.get());
    }
    /**
     * add any needed code to run if the mode changes.
     */
    public void modeChanged() {
        // 
    }
    /**
     * add any needed code to run everytime periodic is called.
     */
    boolean lastCompressorSwitch = compressor1.getPressureSwitchValue();
    boolean lastHoopDown = getHoopDown();
    public void periodic() {
        //  
        boolean b = compressor1.getPressureSwitchValue();
        if (b != lastCompressorSwitch) {
          Robot.getTelemetryTable().putBoolean("pneumatics.pressure", b);
          lastCompressorSwitch = b;
        }
        b = getHoopDown();
        if (b != lastHoopDown) {
          Robot.getTelemetryTable().putBoolean("hoop.armdown", b);
          lastHoopDown = b;
        }
    }
    public void clampDown(){
        clampT0 = 0;
        setClamp(DoubleSolenoid.Value.kForward);
    }
    public void clampUp(){
        if(clampT0 == 0){
          clampT0 = System.currentTimeMillis();
        }
        setClamp(DoubleSolenoid.Value.kReverse);
    }
     
     void setClamp (DoubleSolenoid.Value v) {
        clampCylinder.set(v);
        if (lastClamp != v) {
            updateDriverStationClampDisplay(v);
            lastClamp = v;
        }
     }
     public boolean isClampSetToGoUp() {
         return clampCylinder.get() == DoubleSolenoid.Value.kReverse;
     }
     public boolean getClampUp(){
        boolean rv;
        if (clampT0 == 0) {
            rv = false;
        } else {
            long clampElapsedTime = System.currentTimeMillis() - clampT0;
            if (clampElapsedTime >= Robot.preferences.getDouble(PreferencesNames.CLAMP_DELAY, 500)){             
                rv = true;
            } else {
                rv = false;
            }
        }
        return rv;
     }
     
     void updateDriverStationClampDisplay (DoubleSolenoid.Value v) {
         String s = (v == DoubleSolenoid.Value.kOff) ? "off " : ((v == DoubleSolenoid.Value.kReverse) ? "up  " : "down");
         Robot.driverStationLCD.println(DriverStationLCD.Line.kUser3, 1, "clamp: " + s);
         Robot.driverStationLCD.updateLCD();
     }
    
}