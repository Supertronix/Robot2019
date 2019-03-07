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

	public static TableTournante tableTournante = null; // public pour reservation par systeme de commande

  public Attrapeur(){
	Attrapeur.tableTournante = new TableTournante();
    this.configurerMinirupteur();
  }

  protected TalonSRX moteurAttrapeur = new TalonSRX(MOTEUR_ATTRAPEUR);

  
  // https://www.programcreek.com/java-api-examples/?code=FRC2832/Robot_2016/Robot_2016-master/src/org/usfirst/frc2832/Robot_2016/Climber.java
  // TODO : trouver le numéro de la cremaillere
  protected Servo servoCremaillere = new Servo(SERVO_CREMAILLERE); // Actionne la goupille de relachement de l'écoutille.
  // TODO : trouver les angles maintenue et relachée

  @Override
  public void initDefaultCommand() {
    
  }
   
  public void descendreGoupille(){
	  // Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
	  this.servoCremaillere.set(SERVO_CREMAILLERE_ANGLE_RELACHE);
  }

  public void monterGoupille(){
	  // Servo values range from 0.0 to 1.0 corresponding to the range of full left to full right.
	  this.servoCremaillere.set(SERVO_CREMAILLERE_ANGLE_MAINTENU);
  }

  public void relacherEcoutille(){ // hardillons
	System.out.println("relacherEcoutille() a la vitesse " + MOTEUR_ATTRAPEUR_VITESSE_OUVERTURE);
    moteurAttrapeur.set(ControlMode.PercentOutput, MOTEUR_ATTRAPEUR_VITESSE_OUVERTURE);
  }

  public void armer(){
	System.out.println("armer() a la vitesse " + MOTEUR_ATTRAPEUR_VITESSE_FERMETURE);
    moteurAttrapeur.set(ControlMode.PercentOutput, MOTEUR_ATTRAPEUR_VITESSE_FERMETURE);
  }

  public void configurerMinirupteur(){
    moteurAttrapeur.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    moteurAttrapeur.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    moteurAttrapeur.setNeutralMode(NeutralMode.Brake);
  }
  
  
  
  
  public class TableTournante extends Subsystem  implements RobotMap.Attrapeur.TableTournante
  {
	  // Drive qui fait tourner l'écoutille
	  protected TalonSRX moteurTabletournante = null;
	  
	  public TableTournante()
	  {
		  this.moteurTabletournante = new TalonSRX(MOTEUR_TABLE_TOURNANTE);
	  }
	  
	  public void allumerTableTournante() {
		  System.out.println("allumer la table tournante " + MOTEUR_ATTRAPEUR_VITESSE_FERMETURE);
		  moteurTabletournante.set(ControlMode.PercentOutput, MOTEUR_TABLE_TOURNANTE_VITESSE);
	  }

	  public void eteindreTableTournante() {
		  System.out.println("eteindre la table tournante " + MOTEUR_ATTRAPEUR_VITESSE_FERMETURE);
		  moteurTabletournante.set(ControlMode.PercentOutput, MOTEUR_TABLE_TOURNANTE_ARRET);
	  }

	@Override
	protected void initDefaultCommand() {}
	  
  }
}
