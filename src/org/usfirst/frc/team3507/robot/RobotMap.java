package org.usfirst.frc.team3507.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int DRIVE_CONTROLLER_USB_PORT = 1;
	public static int OPERATOR_CONTROLLER_USB_PORT = 2;
	
	public static int DRIVE_TALON_LEFT_A = 1;
	public static int DRIVE_TALON_LEFT_B = 2;
	public static int DRIVE_TALON_LEFT_C = 3;
	public static int DRIVE_TALON_RIGHT_A = 4;
	public static int DRIVE_TALON_RIGHT_B = 5;
	public static int DRIVE_TALON_RIGHT_C = 6;
	
	public static double JOYSTICK_DEFAULT_DEADBAND = 0.05;
}
