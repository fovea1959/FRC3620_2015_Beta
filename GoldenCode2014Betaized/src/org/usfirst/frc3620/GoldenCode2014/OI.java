// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3620.GoldenCode2014;
import org.usfirst.frc3620.GoldenCode2014.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton reverseToggle;
    public Joystick driveJoystick;
    public JoystickButton extendIntakeButton;
    public JoystickButton cockAndShootStateButton;
    public JoystickButton intakeReverseButton;
    public JoystickButton pusherCylinderButton;
    public JoystickButton joystickButton1;
    public Joystick operationsJoystick;
    public JoystickButton cockAndShootQucklyButton;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        operationsJoystick = new Joystick(2);
         driveJoystick = new Joystick(1);
        joystickButton1 = new JoystickButton(operationsJoystick, 10);
        joystickButton1.whenPressed(new ClampCommand());
        pusherCylinderButton = new JoystickButton(driveJoystick, 4);
        pusherCylinderButton.whenPressed(new PusherCylinderCommand());
        intakeReverseButton = new JoystickButton(operationsJoystick, 5);
        intakeReverseButton.whileHeld(new ReverseIntakeCommand());
        cockAndShootStateButton = new JoystickButton(operationsJoystick, 1);
        cockAndShootStateButton.whenPressed(new CockAndShootState());
        extendIntakeButton = new JoystickButton(operationsJoystick, 6);
        extendIntakeButton.whileHeld(new ExtendIntakeCommand());
        cockAndShootQucklyButton = new JoystickButton(operationsJoystick, 2);
        cockAndShootQucklyButton.whenPressed(new CockAndShootQuickly());
        
        reverseToggle = new JoystickButton(driveJoystick, 3);
        reverseToggle.whenPressed(new DriveToggleReverseCommand());
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("ReverseIntakeCommand", new ReverseIntakeCommand());
        SmartDashboard.putData("AutonomousLowerHoopCommand", new AutonomousLowerHoopCommand());
        SmartDashboard.putData("AutonomousIntakeCommand", new AutonomousIntakeCommand());
        SmartDashboard.putData("AutonomousCockCommand", new AutonomousCockCommand());
        SmartDashboard.putData("AutonomousRaisHoopCommand", new AutonomousRaisHoopCommand());
        SmartDashboard.putData("AutonomousSnuggleCommand", new AutonomousSnuggleCommand());
        SmartDashboard.putData("OneBallAutonomousCommandGroup", new OneBallAutonomousCommandGroup());
        SmartDashboard.putData("AutonomousVisionWaitCommand", new AutonomousVisionWaitCommand());
        SmartDashboard.putData("ClampCommand", new ClampCommand());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }
    public Joystick getOperationsJoystick() {
        return operationsJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}