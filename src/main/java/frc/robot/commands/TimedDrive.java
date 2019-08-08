/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class TimedDrive extends Command {

  Timer timer = new Timer();
  private boolean isFinished;
  private double driveTime;
  private double power;

  /**
   * Drive forward number of seconds.
   * @param driveTime number of seconds to drive forward
   * @param power power to run wheels at, between 0 and 1
   */
  public TimedDrive(double driveTime, double power) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();
    requires(Robot.m_drive);
    this.driveTime = driveTime;
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    isFinished = false;
    // reset encoders
    Robot.m_drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drive.setPower(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (timer.get() > driveTime) {
      isFinished = true;
    }
    else {
      isFinished = false;
    }
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drive.setPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
