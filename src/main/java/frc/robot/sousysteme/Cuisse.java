package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Cuisse extends Subsystem implements RobotMap.Cuisse{

  public Cuisse()
  {
	  this.moteur.configFactoryDefault();	  
	  this.moteurSecondaire.configFactoryDefault();
	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);
	  
	  this.moteurSecondaire.follow(this.moteur);
  }
  

  protected TalonSRX moteur = new TalonSRX(MOTEUR);
  protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);

  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteur.set(ControlMode.PercentOutput, 0.2);
  }

}
