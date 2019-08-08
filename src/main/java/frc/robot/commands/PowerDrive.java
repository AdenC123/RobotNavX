/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class PowerDrive extends Command {


  public PowerDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // reverse stick values
    double stickY = - Robot.m_oi.getStick().getY();
    double stickTwist = - Robot.m_oi.getStick().getTwist();
    double ySign = (stickY > 0.0) ? 1.0 : -1.0;
    double twistSign = (stickTwist > 0.0) ? 1.0 : -1.0;

    // print conditioned stick values    
    //SmartDashboard.putString("DB/String 2", String.format("conditioned y: %4.3f", stickY));
    //SmartDashboard.putString("DB/String 3", String.format("conditioned twist: %4.3f", stickTwist));
    
    // limit power with the GAIN constant
    double powerForward = Math.pow(Math.abs(stickY), Constants.SENSITIVITY) * ySign * Constants.GAIN;
    double powerTwist = Math.pow(Math.abs(stickTwist), Constants.SENSITIVITY) * twistSign * Constants.GAIN * Constants.TURN_SENSITIVITY;

    Robot.m_drive.setPowerArcade(powerForward, powerTwist);

    // print real power values
    //SmartDashboard.putString("DB/String 5", String.format("powerForward %4.3f", powerForward));
    //SmartDashboard.putString("DB/String 6", String.format("powertTwist: %4.3f", powerTwist));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
