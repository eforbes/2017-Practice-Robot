package org.usfirst.frc.team3507.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.RobotMap;

/**
 * The default command for the Drivetrain subsystem
 * Takes in controller input and sets the drivetrain motor values
 * Several smart dashboard options are available
 *
 */
public class DrivetrainControlCommand extends Command {
	
	SendableChooser<DriveControlType> driveTypeSelector = new SendableChooser<>();
	SendableChooser<DriveInputFunction> driveInputFunctionSelector = new SendableChooser<>();
	
	public DrivetrainControlCommand() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		driveTypeSelector.addDefault("Tank", DriveControlType.TANK);
		driveTypeSelector.addObject("Arcade", DriveControlType.ARCADE);
		driveTypeSelector.addObject("Split Arcade", DriveControlType.SPLIT_ARCADE);
		driveTypeSelector.addObject("Paralyzed", DriveControlType.PARALYZED);
		SmartDashboard.putData("Drive control method", driveTypeSelector);
		
		driveInputFunctionSelector.addDefault("Squared", DriveInputFunction.SQUARED);
		driveInputFunctionSelector.addObject("Linear", DriveInputFunction.LINEAR);
		SmartDashboard.putData("Drive input function", driveInputFunctionSelector);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		DriveControlType controlType = driveTypeSelector.getSelected();
		DriveInputFunction inputFunction = driveInputFunctionSelector.getSelected();
		
		switch (controlType) {
		case TANK:
			Robot.drivetrain.tankDrive(
					deadband(Robot.oi.driveController.getY(Hand.kLeft), inputFunction), 
					deadband(Robot.oi.driveController.getY(Hand.kRight), inputFunction));
			break;
		case ARCADE:
			Robot.drivetrain.arcadeDrive(
					deadband(Robot.oi.driveController.getY(Hand.kLeft), inputFunction), 
					deadband(Robot.oi.driveController.getX(Hand.kLeft), inputFunction));
			break;
		case SPLIT_ARCADE:
			Robot.drivetrain.arcadeDrive(
					deadband(Robot.oi.driveController.getY(Hand.kLeft), inputFunction),
					deadband(Robot.oi.driveController.getX(Hand.kRight), inputFunction));
			break;
		case PARALYZED:
			Robot.drivetrain.stop();
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
	
	private enum DriveControlType {
		TANK,
		ARCADE,
		SPLIT_ARCADE,
		PARALYZED
	}
	
	private enum DriveInputFunction {
		SQUARED,
		LINEAR
	}
	
	private double deadband(double value, DriveInputFunction inputFunction) {
		double dead = SmartDashboard.getNumber("Deadband", RobotMap.JOYSTICK_DEFAULT_DEADBAND);
		
		double result = 0.0;
		
    	if (value > dead || value < -dead) {
    		result = ((value - (Math.abs(value) / value * dead)) / (1 - dead));
    	}
    	
    	if(inputFunction == DriveInputFunction.SQUARED) {
    		result *= result;
    	}
    	
    	return result;
	}
}
