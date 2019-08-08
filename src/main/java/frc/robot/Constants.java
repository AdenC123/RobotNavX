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
    
    // GAIN is used to limit the speed of PowerDrive
    public static final double GAIN = 1.0;
    
    // SENSITIVITY is the sensitivity of PowerDrive
    public static double SENSITIVITY = 2.0;

    // MAX_SPEED of motors for SpeedDrive
    public static final double MAX_SPEED = 230;

    // TURN_SENSITIVITY
    public static double TURN_SENSITIVITY = 0.7;

    // PID loop stuff
    public static double DRIVE_KP = 2.0;
    public static double DRIVE_KI = 0.003;
    public static double DRIVE_KF = 4.0;
    public static double INTEGRAL_ZONE = 0.0;
}
