package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Cuisse extends Subsystem implements RobotMap.Cuisse{

  public Cuisse()
  {
	  this.moteurCuissePrincipale.configFactoryDefault();	  
	  this.moteurCuisseSecondaire.configFactoryDefault();
	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);
	  
	  this.moteurCuisseSecondaire.follow(this.moteurCuissePrincipale);
  }
  

  protected TalonSRX moteurCuissePrincipale = new TalonSRX(CUISSE_PRINCIPALE);
  protected TalonSRX moteurCuisseSecondaire = new TalonSRX(CUISSE_SECONDAIRE);
  
 


  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteurCuissePrincipale.set(ControlMode.PercentOutput, 0.2);
  }

}
