package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



// Aussi appelé Hanche par l'équipe	
public class Cuisse extends Subsystem implements RobotMap.Cuisse{

	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	
	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);	
	  //this.moteurPrincipal.setInverted(false);
	  //this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10); // 10 = timeout
	  //this.moteurPrincipal.configAllowableClosedloopError(0, 0, 10); // 10 = timeout
	  //this.moteurPrincipal.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0);
	  //this.moteurPrincipal.overrideLimitSwitchesEnable(true);
	
	int ERREUR_DISTANCE_PERMISE= 5;
  public Cuisse()
  {
	  //this.moteurPrincipal.configFactoryDefault();	  
	  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	  
	  
	  this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	  this.moteurPrincipal.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
	  this.moteurPrincipal.setSensorPhase(true);
	  this.moteurPrincipal.config_kP(0, 1, 10);
	  
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

  // Limit switches
  public void configurerMinirupteur()
  {	  
	  this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	  this.moteurPrincipal.configClearPositionOnLimitR(true, 0);
	  
	  //this.moteurPrincipal.configLimitSwitchDisableNeutralOnLOS(true, 10);
  }
  
  double position; 
  public double lirePosition() // max 3712
  {	  
	  //position = this.encodeur.getDistance();
	  position = this.moteurPrincipal.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  //position = this.moteurPrincipal.getSensorCollection().getQuadraturePosition(); // 742 (limit switch) a -2962
	  System.out.println("Position cuisse " + position);
      SmartDashboard.putNumber("Position cuisse", position);	  
	  return position;
  }
  
  double consigne = 0;
  public void augmenterConsignePID(float increment) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  //Active close loop
		consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, 0, 3700.0);
		this.moteurPrincipal.set(ControlMode.Position, consigne);

  }
  
  public void reduireConsignePID(float decrement) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, 0, 3700.0);
		this.moteurPrincipal.set(ControlMode.Position, consigne);

  }
  
  public double limiterPID(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}
  
  
  public boolean estBloqueParLimite() {
	  System.out.println("estBloquerParLimiteCuisse() "+this.moteurPrincipal.getMotorOutputVoltage());
	  if(this.moteurPrincipal.getMotorOutputVoltage() > 0) {
		  return false;
	  }
	  return true;
  }
  
	public boolean estArrive()
	{
		int distanceRestante = Math.abs((int)(lirePosition() - consigne));
		System.out.println("Distance restante cuisse " + distanceRestante);
		if (distanceRestante <= 500) return true;
		return false;
	}
 
  

}
