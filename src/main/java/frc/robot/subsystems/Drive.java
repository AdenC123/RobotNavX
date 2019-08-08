/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.commands.SpeedDrive;

/**
 * Testing encoder values for 2 seconds at 0.5 speed Going about 83 inches
 * forward <br/>
 * 2133 right <br/>
 * 2110 left <br/>
 * 2122 average <br/>
 * 25.57 ticks per inch <br/>
 * <br/>
 * 6 seconds at 0.5 speed <br/>
 * Going 255 inches <br/>
 * 6562 right <br/>
 * 6500 left <br/>
 * 6531 average <br/>
 * 25.61 ticks per inch <br/>
 * <br/>
 * Same <br/>
 * Drifted to the left <br/>
 * 255 inches <br/>
 * 6587 right <br/>
 * 6483 left <br/>
 * 6535 average <br/>
 * 25.62 ticks per inch <br/>
 * 25.6 ticks per inch average of all 3 tests <br/>
 * 
 * DistanceDrive for 240 inches, went 244 inches 6281 right 6209 left
 */

public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  static public final double TICKS_PER_INCH = 25.6;

  private double targetLeftSpeed;
  private double targetRightSpeed;

  final public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMaster),
      rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1), rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2),
      leftMaster = new WPI_TalonSRX(RobotMap.leftMaster), leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1),
      leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2);

  public Drive() {
    super();
    // constructs and configures all six drive motors
    // restore everything to known factory default state
    rightMaster.configFactoryDefault();
    rightSlave1.configFactoryDefault();
    rightSlave2.configFactoryDefault();
    leftMaster.configFactoryDefault();
    leftSlave1.configFactoryDefault();
    leftSlave2.configFactoryDefault();
    // now configure them
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.setInverted(InvertType.FollowMaster);
    rightSlave2.setInverted(InvertType.FollowMaster);
    leftSlave1.setInverted(InvertType.FollowMaster);
    leftSlave2.setInverted(InvertType.FollowMaster);
    setNeutralMode(NeutralMode.Brake);
    rightMaster.setInverted(InvertType.InvertMotorOutput);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    rightMaster.config_kP(0, Constants.DRIVE_KP);
    rightMaster.config_kI(0, Constants.DRIVE_KI);
    rightMaster.config_IntegralZone(0, (int)(Constants.INTEGRAL_ZONE * Constants.MAX_SPEED));
    rightMaster.config_kD(0, 0);
    rightMaster.config_kF(0, Constants.DRIVE_KF);
    rightMaster.setSensorPhase(false);
    leftMaster.config_kP(0, Constants.DRIVE_KP);
    leftMaster.config_kI(0, Constants.DRIVE_KI);
    leftMaster.config_IntegralZone(0, (int)(Constants.INTEGRAL_ZONE * Constants.MAX_SPEED));
    leftMaster.config_kD(0, 0);
    leftMaster.config_kF(0, Constants.DRIVE_KF);
    leftMaster.setSensorPhase(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new SpeedDrive());
  }

  public void setNeutralMode(NeutralMode mode) {
    // method to easily set the neutral mode of all of the driveTrain motors
    rightMaster.setNeutralMode(mode);
    rightSlave1.setNeutralMode(mode);
    rightSlave2.setNeutralMode(mode);
    leftMaster.setNeutralMode(mode);
    leftSlave1.setNeutralMode(mode);
    leftSlave2.setNeutralMode(mode);
  }

  // 2 commands to simply set the power of the left and right side of the robot
  public void setPower(double rightPower, double leftPower) {
    rightMaster.set(ControlMode.PercentOutput, rightPower);
    leftMaster.set(ControlMode.PercentOutput, leftPower);
  }

  public void setPower(double power) {
    rightMaster.set(ControlMode.PercentOutput, power);
    leftMaster.set(ControlMode.PercentOutput, power);
  }

  // Drive the robot using a joystick
  public void setPowerArcade(double forward, double turn) {
    double max = Math.abs(forward) + Math.abs(turn);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
    rightMaster.set(ControlMode.PercentOutput, scale * (forward + turn));
    leftMaster.set(ControlMode.PercentOutput, scale * (forward - turn));
  }

  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition();
  }

  public double getLeftPosition() {
    return leftMaster.getSelectedSensorPosition();
  }

  public void resetEncoders() {
    rightMaster.setSelectedSensorPosition(0, 0, 10);
    leftMaster.setSelectedSensorPosition(0, 0, 10);
  }

  public double getRightSpeed() {
    return rightMaster.getSelectedSensorVelocity();
  }

  public double getLeftSpeed() {
    return leftMaster.getSelectedSensorVelocity();
  }

  public int getControlMode() {
    return rightMaster.getControlMode().value;
  }

  public void resetIntegral() {
    rightMaster.setIntegralAccumulator(0);
    leftMaster.setIntegralAccumulator(0);
  }

  public double getTargetRightSpeed() {
    return targetRightSpeed;
  }

  public double getTargetLeftSpeed() {
    return targetLeftSpeed;
  }

  // WIP
  public void setSpeedArcade(double forward, double turn) {

    double max = Math.abs(forward) + Math.abs(turn);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);

    rightMaster.config_kP(0, Constants.DRIVE_KP);
    leftMaster.config_kP(0, Constants.DRIVE_KP);
    rightMaster.config_kI(0, Constants.DRIVE_KI);
    leftMaster.config_kI(0, Constants.DRIVE_KI);
    rightMaster.config_IntegralZone(0, (int)(Constants.INTEGRAL_ZONE * Constants.MAX_SPEED));
    leftMaster.config_IntegralZone(0, (int)(Constants.INTEGRAL_ZONE * Constants.MAX_SPEED));
    rightMaster.config_kF(0, Constants.DRIVE_KF);
    leftMaster.config_kF(0, Constants.DRIVE_KF);

    targetRightSpeed = scale * (forward + turn) * Constants.MAX_SPEED;
    targetLeftSpeed = scale * (forward - turn) * Constants.MAX_SPEED;
    
    rightMaster.set(ControlMode.Velocity, targetRightSpeed);
    leftMaster.set(ControlMode.Velocity, targetLeftSpeed);
  }
}
