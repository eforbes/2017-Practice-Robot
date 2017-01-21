package org.usfirst.frc.team3507.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3507.robot.Robot;

/**
 *
 */
public class DrivetrainControlCommand extends Command {
	public DrivetrainControlCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double left = Robot.oi.driveController.getX();
		double right = Robot.oi.driveController.getY();
		
		Robot.drivetrain.set(left, right);
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
}
