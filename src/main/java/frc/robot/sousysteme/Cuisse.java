package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

public class Cuisse extends Subsystem implements RobotMap.Cuisse{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);

	//protected Encoder encodeurMoteurPrincipal = new Encoder(ENCODEUR_MOTEUR_PRINCIPAL_A, ENCODEUR_MOTEUR_PRINCIPAL_B,  ENCODEUR_MOTEUR_PRINCIPAL_INVERSION, Encoder.EncodingType.k2X);

	
  public Cuisse()
  {
	  this.moteurPrincipal.configFactoryDefault();	  
	  this.moteurSecondaire.configFactoryDefault();
	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);
	  
	  this.moteurSecondaire.follow(this.moteurPrincipal);
  } 
  
  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, 0.2);
  }

}
