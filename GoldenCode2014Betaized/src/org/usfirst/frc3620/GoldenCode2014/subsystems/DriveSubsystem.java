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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3620.GoldenCode2014.Robot;
import org.usfirst.frc3620.GoldenCode2014.RobotMode;
import org.usfirst.frc3620.GoldenCode2014.commands.DriveArcadeCommand;
/**
 *
 */
public class DriveSubsystem extends Subsystem {
    NetworkTable telemetryTable = Robot.getTelemetryTable();
    boolean reverse = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController speedController2 = RobotMap.driveSubsystemSpeedController2;
    SpeedController speedController3 = RobotMap.driveSubsystemSpeedController3;
    SpeedController speedController5 = RobotMap.driveSubsystemSpeedController5;
    SpeedController speedController6 = RobotMap.driveSubsystemSpeedController6;
    RobotDrive primaryDrive = RobotMap.driveSubsystemPrimaryDrive;
    SpeedController speedController4 = RobotMap.driveSubsystemSpeedController4;
    SpeedController speedController1 = RobotMap.driveSubsystemSpeedController1;
    RobotDrive turboDrive = RobotMap.driveSubsystemTurboDrive;
    Encoder driveEncoder2 = RobotMap.driveSubsystemDriveEncoder2;
    Encoder driveEncoder1 = RobotMap.driveSubsystemDriveEncoder1;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new DriveArcadeCommand());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void arcadeDrive(GenericHID hid) {
        telemetryTable.putNumber("js.x", hid.getX());
        telemetryTable.putNumber("js.y", hid.getY());
        telemetryTable.putNumber("js.z", hid.getZ());
        telemetryTable.putNumber("js.throttle", hid.getThrottle());
        telemetryTable.putNumber("js.twist", hid.getTwist());
        double rotate = hid.getThrottle();
        double move = hid.getY();
        //if (SmartDashboard.getBoolean("drive.squared")) {
        double r2 = Math.sqrt(Math.abs(rotate * rotate * rotate));
        if (rotate < 0) {
            r2 = -r2;
        }
        double m2 = Math.abs(move * move);
        if (move < 0) {
            m2 = -m2;
        }
        if (reverse == true) {
            primaryDrive.arcadeDrive(-m2, r2);
            turboDrive.arcadeDrive(-m2, r2);
        } else {
            primaryDrive.arcadeDrive(m2, r2);
            turboDrive.arcadeDrive(m2, r2);
        }
    }
    public void toggleReverseMode() {
        // use setReverseMode so that the dashboard gets updated
        setReverseMode(!reverse);
    }
    public void setReverseMode(boolean newReverse) {
        reverse = newReverse;
        SmartDashboard.putString("reverseMode", reverse ? "reverse" : "forward");
        telemetryTable.putBoolean("drive.reversed", reverse);
    }
    public boolean getReverseMode() {
        return reverse;
    }
    
    public void allForward(double motorPower) {
        double p;
        p = motorPower;
        // we need to negate the power number
        // because we are emulating a joystick, and 
        // forward on a joysick is a neagtive y value.
        primaryDrive.arcadeDrive(-p, 0);
        turboDrive.arcadeDrive(-p, 0);
    }
    
    public void allStop() {
        primaryDrive.stopMotor();
        turboDrive.stopMotor();
    }
    
    /**
     * add any needed code to run when robot powers up.
     */
    public void init() {
        SmartDashboard.putBoolean("okToFire", false);
        setReverseMode(false);
        resetEncoders();
        driveEncoder1.start();
        driveEncoder2.start();
    }
    /**
     * add any needed code to run if the mode changes.
     */
    public void modeChanged() {
        if (Robot.getCurrentRobotMode() == RobotMode.TELEOP) {
            setReverseMode(false);
        }
         if (Robot.getCurrentRobotMode() == RobotMode.DISABLED) {
            resetEncoders();
        }
    }
    /**
     * add any needed code to run everytime periodic is called.
     */
    public void periodic() {
        //  
        telemetryTable.putNumber("drive.turbo.left", speedController1.get());
        telemetryTable.putNumber("drive.main.left", speedController2.get());
        telemetryTable.putNumber("drive.turbo.right", speedController4.get());
        telemetryTable.putNumber("drive.main.right", speedController5.get());
        telemetryTable.putNumber("drive.encoder1", driveEncoder1.getDistance());
        telemetryTable.putNumber("drive.encoder2", driveEncoder2.getDistance());
    }
    public void resetEncoders(){
        driveEncoder1.reset();
        driveEncoder2.reset();
    }
    public double getEncoderDistance(){
        return driveEncoder1.getDistance();
    }
  
}