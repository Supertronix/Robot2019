package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

// Aussi appelé Genoux par l'équipe
public class Jambe extends Subsystem implements RobotMap.Jambe{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	
	//protected Encoder encodeurMoteurPrincipal = new Encoder(ENCODEUR_MOTEUR_PRINCIPAL_A, ENCODEUR_MOTEUR_PRINCIPAL_B,  ENCODEUR_MOTEUR_PRINCIPAL_INVERSION, Encoder.EncodingType.k2X);
	
	public Jambe() {
		  this.moteurPrincipal.configFactoryDefault();
		  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	
		  //this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  
		  this.moteurSecondaire.configFactoryDefault();
		  this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
		  this.moteurSecondaire.follow(this.moteurPrincipal);
		  
		  //encodeurGenouxPrincipal = new Encoder()
	}

	@Override
	protected void initDefaultCommand() {}
	
	public void monter()
	{
		this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
	}
	public void monter(float vitesse)
	{
		this.moteurPrincipal.set(ControlMode.PercentOutput, vitesse);
	}
	
}
