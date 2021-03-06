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


import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3620.GoldenCode2014.RobotMap;
import org.usfirst.frc3620.GoldenCode2014.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3620.GoldenCode2014.PreferencesNames;
import org.usfirst.frc3620.GoldenCode2014.Robot;
import org.usfirst.frc3620.GoldenCode2014.RobotMode;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    double lastPower = 0;
    NetworkTable telemetryTable = Robot.getTelemetryTable();
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController intakeMotor = RobotMap.intakeSubsystemIntakeMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void intakeMotorReverse() {
        intakeMotor.set(0.5);
        Robot.getTelemetryTable().putNumber("intake.power", intakeMotor.get());
    }

    public void intakeMotorOn() {
        double motorSpeed = Robot.preferences.getDouble(PreferencesNames.INTAKE_SPEED, 50);
        intakeMotor.set(-motorSpeed / 100.0);
        Robot.getTelemetryTable().putNumber("intake.power", intakeMotor.get());
    }

    public void intakeMotorOff() {
        intakeMotor.set(0);
        Robot.getTelemetryTable().putNumber("intake.power", intakeMotor.get());
    }

    /**
     * add any needed code to run when robot powers up.
     */
    public void init() {
        //
        lastPower = -2.0; // make sure that periodoc updates the DS the first time
        intakeMotorOff();
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
    public void periodic() {
        //  
        double power = intakeMotor.get();
        if (power != lastPower) {
            telemetryTable.putNumber("intake.power", power);
            String s = (power == 0) ? "off    " : ((power > 0) ? "reverse" : "forward");
            lastPower = power;
        }
    }
}
