package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

// Aussi appelé Hanche par l'équipe	
public class Cuisse extends Subsystem implements RobotMap.Cuisse{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	//protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	protected Encoder encodeurMoteurPrincipal = null;

	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);	
  public Cuisse()
  {
	  this.moteurPrincipal.configFactoryDefault();	  
	  this.encodeurMoteurPrincipal = new Encoder(0, 1, false, Encoder.EncodingType.k2X);
	  this.configurerMinirupteur();
	  
	  //this.moteurSecondaire.configFactoryDefault();	  
	  //this.moteurSecondaire.setInverted(true);
	  //this.moteurSecondaire.follow(this.moteurPrincipal);
  } 
  
  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
	//this.moteurSecondaire.set(ControlMode.PercentOutput, 0.1);
  }
  public void monter(float vitesse)
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, vitesse);
	//this.moteurSecondaire.set(ControlMode.PercentOutput, vitesse);
  }
  
  public void configurerMinirupteur(){
	moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	moteurPrincipal.setNeutralMode(NeutralMode.Brake);
  }
  

}
