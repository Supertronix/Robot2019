package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Genoux extends Subsystem implements RobotMap.Genoux{

	protected TalonSRX genouxPrincipale = new TalonSRX(GENOUX_PRINCIPAL);
	protected TalonSRX genouxSecondaire = new TalonSRX(GENOUX_SECONDAIRE);
	
	public Genoux() {
		  this.genouxPrincipale.configFactoryDefault();
		  this.genouxSecondaire.configFactoryDefault();
		  this.genouxSecondaire.follow(this.genouxSecondaire);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
