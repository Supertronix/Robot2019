package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_SECONDAIRE);
	//protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	
	//protected Encoder encodeur = null;

	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);	
	  //this.moteurPrincipal.setInverted(false);
	  //this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10); // 10 = timeout
	  //this.moteurPrincipal.configAllowableClosedloopError(0, 0, 10); // 10 = timeout
	  //this.moteurPrincipal.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0);
	  //this.moteurPrincipal.overrideLimitSwitchesEnable(true);
  public Cuisse()
  {
	  this.moteurPrincipal.configFactoryDefault();	  
	  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	  
	  
	  //this.encodeur = new Encoder(1,0, true, Encoder.EncodingType.k2X);
	  //this.encodeur.setDistancePerPulse(1);// https://www.reddit.com/r/FRC/comments/53ejdl/initializing_and_using_an_encoder_in_java/
	  
	  //this.moteurPrincipal.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	  this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

	 // this.configurerMinirupteur();
	  
	  //this.moteurSecondaire.configFactoryDefault();	  
	  //this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
	  //this.moteurSecondaire.setInverted(true);
	  //this.moteurSecondaire.follow(this.moteurPrincipal);
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

  // Limit switches
  public void configurerMinirupteur(){	  
  }
  
  double position;
  public double lirePosition()
  {	  
	  //position = this.encodeur.getDistance();
	  position = this.moteurPrincipal.getSelectedSensorPosition();
	  System.out.println("Position cuisse " + position);
      SmartDashboard.putNumber("Position cuisse", position);	  
	  return position;
  }
  

}
