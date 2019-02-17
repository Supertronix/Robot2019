package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Attrapeur extends Subsystem implements RobotMap.Attrapeur{

  public Attrapeur(){
    this.configurerMinirupteur();
  }

  protected TalonSRX moteurAttrapeur = new TalonSRX(MOTEUR_ATTRAPEUR);

  // Drive qui fait tourner l'écoutille
  // TODO : trouver le numéro de la drive de la table tournante
  protected TalonSRX moteurTabletournante = new TalonSRX(MOTEUR_TABLE_TOURNANTE);

  
  // https://www.programcreek.com/java-api-examples/?code=FRC2832/Robot_2016/Robot_2016-master/src/org/usfirst/frc2832/Robot_2016/Climber.java
  // TODO : trouver le numéro de la cremaillere
  protected Servo servoCremaillere = new Servo(SERVO_CREMAILLERE); // Actionne la goupille de relachement de l'écoutille.
  // TODO : trouver les angles maintenue et relachée

  @Override
  public void initDefaultCommand() {
    
  }
  
  public void activerTableTournante() {
	  moteurTabletournante.set(ControlMode.PercentOutput, MOTEUR_TABLE_TOURNANTE_VITESSE);
  }

  public void desactiverTableTournante() {
	  moteurTabletournante.set(ControlMode.PercentOutput, MOTEUR_TABLE_TOURNANTE_ARRET);
  }
 
  public void descendreGoupille(){
	  // Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
	  this.servoCremaillere.set(SERVO_CREMAILLERE_ANGLE_RELACHEE);
  }

  public void monterGoupille(){
	  // Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
	  this.servoCremaillere.set(SERVO_CREMAILLERE_ANGLE_MAINTENUE);
  }

  public void relacherEcoutille(){
    moteurAttrapeur.set(ControlMode.PercentOutput, MOTEUR_ATTRAPEUR_VITESSE_OUVERTURE);
  }

  public void armer(){
    moteurAttrapeur.set(ControlMode.PercentOutput, MOTEUR_ATTRAPEUR_VITESSE_FERMETURE);
  }

  public void configurerMinirupteur(){
    moteurAttrapeur.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    moteurAttrapeur.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    moteurAttrapeur.setNeutralMode(NeutralMode.Brake);
  }
}
