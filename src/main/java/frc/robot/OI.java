/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import frc.robot.commands.DistanceDrive;
//import frc.robot.commands.ResetEncoders;
//import frc.robot.commands.AdjustSensitivity;
import frc.robot.commands.AdjustkF;
import frc.robot.commands.AdjustkP;
import frc.robot.commands.AdjustkI;
import frc.robot.commands.AdjustIntegralZone;
import frc.robot.commands.AdjustTurnSensitivity;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //adding joystick and trigger
  private final Joystick stick = new Joystick(0);

  private final JoystickButton button11 = new JoystickButton(this.stick, 11);
  private final JoystickButton sideButton = new JoystickButton(this.stick, 2);
  private final JoystickButton button12 = new JoystickButton(this.stick, 12);
  private final JoystickButton button6 = new JoystickButton(this.stick, 6);
  private final JoystickButton button4 = new JoystickButton(this.stick, 4);
  private final JoystickButton button5 = new JoystickButton(this.stick, 5);
  private final JoystickButton button3 = new JoystickButton(this.stick, 3);
  private final JoystickButton button10 = new JoystickButton(this.stick, 10);
  private final JoystickButton button9 = new JoystickButton(this.stick, 9);
  private final JoystickButton button7 = new JoystickButton(this.stick, 7);
  private final JoystickButton button8 = new JoystickButton(this.stick, 8);
  
  //method to be called by other commands or subsystems to use the joystick
  public Joystick getStick() {
    return (stick);
  }

  public OI(){
    //button11.whenPressed(new TimedDrive(6.0, 0.5));
    sideButton.whenPressed(Robot.m_drive.getDefaultCommand());
    //button12.whenPressed(new DistanceDrive(240, 0.5));
    //button11.whenPressed(new ResetEncoders());
    button12.whenPressed(new AdjustTurnSensitivity(0.1));
    button11.whenPressed(new AdjustTurnSensitivity(-0.1));
    //button6.whenPressed(new AdjustSensitivity(0.1));
    //button4.whenPressed(new AdjustSensitivity(-0.1));
    button6.whenPressed(new AdjustkF(0.1));
    button4.whenPressed(new AdjustkF(-0.1));
    button5.whenPressed(new AdjustkP(0.1));
    button3.whenPressed(new AdjustkP(-0.1));
    button9.whenPressed(new AdjustkI(-0.001));
    button10.whenPressed(new AdjustkI(0.001));
    button8.whenPressed(new AdjustIntegralZone(0.05));
    button7.whenPressed(new AdjustIntegralZone(-0.05));

  }

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
