package org.usfirst.frc.team3620.robot;

import javax.naming.PartialResultException;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
public DoubleSolenoid d01, d45;
public Solenoid d2, d3;
public Joystick joystick;
public JoystickButton button1;
	Compressor compressor;
	AnalogInput analog0;
	PowerDistributionPanel pdp;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		System.out.println ("Robot init");
		
		// pdp = new PowerDistributionPanel();
		
		// it's ok with no Analog
		// it's ok with initializating analog before starting compressor
		// it's ok with initializing compressor, then initialiing input
		
		compressor = new Compressor();
		updatePressureSwitchReading();
		compressor.start();
		analog0 = new AnalogInput(0);
		d01 = new DoubleSolenoid(0, 1);
		joystick = new Joystick(1);
		button1 = new JoystickButton(joystick, 1);
		d45 = new DoubleSolenoid(4, 5);
		d2 = new Solenoid(2);
		d3 = new Solenoid(3);
		
		System.out.println(d01.get());
	}
	
	public void periodic () {
		if (analog0 != null) analog0.getVoltage();
		if (pdp != null) {
			System.out.printf ("PDP: voltage %f\n", pdp.getVoltage());
		}
		
		updatePressureSwitchReading();
	}
	
	PressureSwitchState lastPressureSwitch = PressureSwitchState.UNKNOWN;
	
	public void updatePressureSwitchReading() {
		PressureSwitchState pressureSwitch = PressureSwitchState.UNKNOWN;
		try {
			pressureSwitch = compressor.getPressureSwitchValue() ? PressureSwitchState.TRUE : PressureSwitchState.FALSE;
		} catch (Exception e) {
			pressureSwitch = PressureSwitchState.TOOKEXCEPTION;
		}
		
		if (lastPressureSwitch != pressureSwitch) {
			System.out.printf ("Pressure switch went from %s to %s\n", lastPressureSwitch, pressureSwitch);
		}
		lastPressureSwitch = pressureSwitch;
	}

	public void autonomousInit() {
		System.out.println ("autonomous init");
	}

	public void autonomousPeriodic() {
		periodic();
	}

	public void teleopInit() {
		System.out.println ("teleop init");
	}

	public void teleopPeriodic() {
		periodic();
		if(button1.get()){
			System.out.println("button down");
			d01.set(Value.kForward);
			d45.set(Value.kReverse);
			d2.set(true);
			d3.set(false);
		}
		else{
			System.out.println("button up");
		d01.set(Value.kReverse);
		d45.set(Value.kForward);
		d2.set(false);
		d3.set(true);
		}
		System.out.println(d01.get());
	}

	public void testInit() {
		System.out.println ("test init");
	}

	public void testPeriodic() {
		LiveWindow.run();
		periodic();
	}

	public void disabledInit() {
		System.out.println ("disabled");
	}

	public void disabledPeriodic() {
		periodic();
	}
	
	enum PressureSwitchState {
		UNKNOWN, TRUE, FALSE, TOOKEXCEPTION;
	}
}
