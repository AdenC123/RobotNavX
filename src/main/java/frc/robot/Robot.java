/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static final Drive m_drive = new Drive();
  public static OI m_oi;

  // navx thing
  private static AHRS ahrs;

  static public AHRS getAHRS() {
    return ahrs;
  }

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //function to display data about the robot
  void displayDriveParameters() {
    SmartDashboard.putString("DB/String 0", String.format("target right: %4.3f", Robot.m_drive.getTargetRightSpeed()));
    SmartDashboard.putString("DB/String 1", String.format("target left: %4.3f", Robot.m_drive.getTargetLeftSpeed()));
    SmartDashboard.putString("DB/String 2", String.format("right encoder: %4.3f", Robot.m_drive.getRightPosition()));
    SmartDashboard.putString("DB/String 3", String.format("left encoder: %4.3f", Robot.m_drive.getLeftPosition()));
    //SmartDashboard.putString("DB/String 4", String.format("SENSITIVITY: %4.3f", Constants.SENSITIVITY));
    //SmartDashboard.putString("DB/String 4", String.format("DRIVE_KF: %4.3f", Constants.DRIVE_KF));
    SmartDashboard.putString("DB/String 5", String.format("right speed: %4.3f", Robot.m_drive.getRightSpeed()));
    SmartDashboard.putString("DB/String 6", String.format("left speed: %4.3f", Robot.m_drive.getLeftSpeed()));
    SmartDashboard.putString("DB/String 7", String.format("yaw: %7.3f", ahrs.getYaw()));
    SmartDashboard.putString("DB/String 8", String.format("pitch: %7.3f", ahrs.getPitch()));
    SmartDashboard.putString("DB/String 9", String.format("roll: %7.3f", ahrs.getRoll()));
}
 /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {
    m_oi = new OI();
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    for (int i = 0; i < 10; i++) {
      SmartDashboard.putString(String.format("DB/String %d",i), " ");
    }

    // reset encoders during init
    Robot.m_drive.resetEncoders();

    // navx setup
    try {
      System.out.println("Initialize navx");
      ahrs = new AHRS(SerialPort.Port.kUSB1);
      System.out.println("Initialized navx");
      System.out.println(ahrs.toString());
      ahrs.reset();
      while (ahrs.isCalibrating()) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          break;
        }
      }
    } catch (Throwable e) {
      System.out.println(e);
      ahrs = null;
    }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    displayDriveParameters();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    displayDriveParameters();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    displayDriveParameters();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    displayDriveParameters();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
