/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {
    
    // Gains used in SpeedDrive
    public static final double FORWARD_GAIN = 1.0;
    public static final double TURN_GAIN = 1.0;
    
    // Sensitivites of SpeedDrive
    public static final double FORWARD_SENSITIVITY = 2.0;
    public static final double TURN_SENSITIVITY = 3.0;

    // MAX_SPEED of motors for SpeedDrive
    public static final double MAX_SPEED = 230;

    // PID loop stuff
    public static final double DRIVE_KP = 2.0;
    public static final double DRIVE_KI = 0.003;
    public static final double DRIVE_KF = 4.0;
    public static final double INTEGRAL_ZONE = 0.0;

    public static final double DRIVE_DEADBAND = 0.05;
}
