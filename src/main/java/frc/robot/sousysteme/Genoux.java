package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;



public class Genoux extends Subsystem implements RobotMap.Genoux{

	protected TalonSRX moteurGenouxPrincipale = new TalonSRX(MOTEUR_GENOUX_PRINCIPAL);
	protected TalonSRX moteurGenouxSecondaire = new TalonSRX(MOTEUR_GENOUX_SECONDAIRE);
	
	protected Encoder encodeurGenouxPrincipal;

	
	public Genoux() {
		  this.moteurGenouxPrincipale.configFactoryDefault();
		  this.moteurGenouxSecondaire.configFactoryDefault();
		  this.moteurGenouxSecondaire.follow(this.moteurGenouxSecondaire);
		  
		  
		  //encodeurGenouxPrincipal = new Encoder()
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
