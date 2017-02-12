package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.DrivetrainControlCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private CANTalon talonLeftA = new CANTalon(RobotMap.DRIVE_TALON_LEFT_A);
	private CANTalon talonLeftB = new CANTalon(RobotMap.DRIVE_TALON_LEFT_B);
	private CANTalon talonLeftC = new CANTalon(RobotMap.DRIVE_TALON_LEFT_C);
	
	private CANTalon talonRightA = new CANTalon(RobotMap.DRIVE_TALON_RIGHT_A);
	private CANTalon talonRightB = new CANTalon(RobotMap.DRIVE_TALON_RIGHT_B);
	private CANTalon talonRightC = new CANTalon(RobotMap.DRIVE_TALON_RIGHT_C);
	
	private RobotDrive robotDrive;
	
	public Drivetrain() {
		changeTalonMode(talonLeftA, TalonControlMode.PercentVbus, 0);
		changeTalonMode(talonLeftB, TalonControlMode.Follower, RobotMap.DRIVE_TALON_LEFT_A);
		changeTalonMode(talonLeftC, TalonControlMode.Follower, RobotMap.DRIVE_TALON_LEFT_A);
		
		changeTalonMode(talonRightA, TalonControlMode.PercentVbus, 0);
		changeTalonMode(talonRightB, TalonControlMode.Follower, RobotMap.DRIVE_TALON_RIGHT_A);
		changeTalonMode(talonRightC, TalonControlMode.Follower, RobotMap.DRIVE_TALON_RIGHT_A);
		
		talonRightA.setInverted(true);
		
		robotDrive = new RobotDrive(talonLeftA, talonRightA);
		robotDrive.setSafetyEnabled(false);
	}
	
	public void initDefaultCommand() {
		 setDefaultCommand(new DrivetrainControlCommand());
	}
	
	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
	}
	
	public void arcadeDrive(double move, double rotate) {
		robotDrive.arcadeDrive(move, rotate);
	}
	
	public void stop() {
		robotDrive.stopMotor();
	}
	
	private void changeTalonMode(CANTalon talon, TalonControlMode mode, double initialValue) {
		talon.changeControlMode(mode);
		talon.set(initialValue);
	}
}
