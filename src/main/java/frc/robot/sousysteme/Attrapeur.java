package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Attrapeur extends Subsystem implements RobotMap.Attrapeur{

  public Attrapeur(){
    this.configurerLimitSwitch();
  }

  protected TalonSRX moteur = new TalonSRX(MOTEUR_ATTRAPEUR);

  @Override
  public void initDefaultCommand() {
    
  }

  public void relacherEcoutille(){
    moteur.set(ControlMode.PercentOutput, VITESSE_OUVERTURE);
  }

  public void armer(){
    moteur.set(ControlMode.PercentOutput, VITESSE_FERMETURE);
  }

  public void configurerLimitSwitch(){
    moteur.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, 
    LimitSwitchNormal.NormallyClosed);
    moteur.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, 
    LimitSwitchNormal.NormallyClosed);
    moteur.setNeutralMode(NeutralMode.Brake);
  }
}
