/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Attrapeur extends Subsystem implements RobotMap.Attrapeur{

  public Attrapeur(){
    this.configurerLimitSwitch();
  }

  protected TalonSRX moteur = new TalonSRX(MOTEUR_ATTRAPEUR);

  @Override
  public void initDefaultCommand() {
    
  }

  public void activerMoteur(){
    moteur.set(ControlMode.PercentOutput, -0.1);
  }

  public void configurerLimitSwitch(){
    moteur.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    moteur.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
  }
}
