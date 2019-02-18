package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;



public class Genoux extends Subsystem implements RobotMap.Genoux{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	
	//protected Encoder encodeurMoteurPrincipal = new Encoder(ENCODEUR_MOTEUR_PRINCIPAL_A, ENCODEUR_MOTEUR_PRINCIPAL_B,  ENCODEUR_MOTEUR_PRINCIPAL_INVERSION, Encoder.EncodingType.k2X);
	
	public Genoux() {
		  this.moteurPrincipal.configFactoryDefault();
		  this.moteurSecondaire.configFactoryDefault();
		  this.moteurSecondaire.follow(this.moteurSecondaire);
		  
		  //encodeurGenouxPrincipal = new Encoder()
	}

	@Override
	protected void initDefaultCommand() {}
}
