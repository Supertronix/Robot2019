package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// Aussi appelé Hanche par l'équipe	
public class Cuisse extends Subsystem implements RobotMap.Cuisse{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);

	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);	
  public Cuisse()
  {
	  this.moteurPrincipal.configFactoryDefault();	  
	  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	  
	  //this.moteurPrincipal.setInverted(false);
	  //this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10); // 10 = timeout
	  //this.moteurPrincipal.configAllowableClosedloopError(0, 0, 10); // 10 = timeout
	  //this.moteurPrincipal.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0);
	  this.configurerMinirupteur();
	  
	  this.moteurSecondaire.configFactoryDefault();	  
	  this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
	  this.moteurSecondaire.setInverted(true);
	  this.moteurSecondaire.follow(this.moteurPrincipal);
  } 
  
  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
  }
  public void monter(float vitesse)
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, vitesse);
  }
  
  public void configurerMinirupteur(){
	moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
  }
  
  int position;
  public int lirePosition()
  {	  
	  position = this.moteurPrincipal.getSelectedSensorPosition();
	  System.out.println("Position cuisse " + position);
      SmartDashboard.putNumber("Position cuisse", position);	  
	  return position;
  }
  

}
