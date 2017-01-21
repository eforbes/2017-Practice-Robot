package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.DrivetrainControlCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon leftTalon = new CANTalon(RobotMap.leftDriveTalon);
	private CANTalon rightTalon = new CANTalon(RobotMap.rightDriveTalon);
	
	public Drivetrain() {
		//voltage is the normal control mode: accepts values between -1.0 and 1.0,
		// -1.0 is full reverse
		// 1.0 is full forward
		// 0 is stop
		leftTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		rightTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		 setDefaultCommand(new DrivetrainControlCommand());
	}
	
	public void set(double left, double right) {
		leftTalon.set(left);
		rightTalon.set(right);
	}
	
	public void stop() {
		leftTalon.set(0.0);
		rightTalon.set(0.0);
	}
}
