/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class DistanceDrive extends Command {

  private double distance;
  private double power;
  private double targetTicks;

  /**
   * Drive a certain distance
   * @param distance Distance to travel in inches
   * @param power Power between 0 and 1
   */
  public DistanceDrive(double distance, double power) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drive);
    this.distance = distance;
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drive.resetEncoders();
    targetTicks = distance * Drive.TICKS_PER_INCH;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drive.setPower(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double averageCurrentTicks = (Robot.m_drive.getRightPosition() + Robot.m_drive.getLeftPosition()) / 2;
    
    if (averageCurrentTicks > targetTicks) {
      return true;
    }
    return false;
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
