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
