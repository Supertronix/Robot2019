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

// Aussi appelé Genoux par l'équipe
public class Jambe extends Subsystem implements RobotMap.Jambe{

	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	
	//protected Encoder encodeurMoteurPrincipal = new Encoder(ENCODEUR_MOTEUR_PRINCIPAL_A, ENCODEUR_MOTEUR_PRINCIPAL_B,  ENCODEUR_MOTEUR_PRINCIPAL_INVERSION, Encoder.EncodingType.k2X);
	
	int ERREUR_DISTANCE_PERMISE = 5;
	public Jambe() {
		  //this.moteurPrincipal.configFactoryDefault();
		  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	
		  //this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		  this.moteurPrincipal.configAllowableClosedloopError(0, 0, ERREUR_DISTANCE_PERMISE);
		  this.moteurPrincipal.setSensorPhase(true);
		  this.moteurPrincipal.config_kP(0, 1, 9);
		  

		  configurerMinirupteur();
		  
		  //this.moteurSecondaire.configFactoryDefault();
		  this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
		  this.moteurSecondaire.setInverted(false);
		  this.moteurSecondaire.follow(this.moteurPrincipal);

		  
		  //encodeurGenouxPrincipal = new Encoder()
	}

	@Override
	protected void initDefaultCommand() {}
	
	
	double consigne = 0;
	public void augmenterConsignePID(float increment) {
		  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		  //Active close loop
			consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, 0, 7923.0);
			this.moteurPrincipal.set(ControlMode.Position, consigne);

	  }
	  
	  public void reduireConsignePID(float decrement) {
		  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		  consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, 0, 7923.0);
			this.moteurPrincipal.set(ControlMode.Position, consigne);

	  }
	  
	  double position; 
	  public double lirePosition()
	  {	  
		  //position = this.encodeur.getDistance();
		  position = this.moteurPrincipal.getSelectedSensorPosition(); // 0-7923 
		  //position = this.moteurPrincipal.getSensorCollection().getQuadraturePosition(); 
		  System.out.println("Position jambe " + position);
	      SmartDashboard.putNumber("Position jambe", position);	  
		  return position;
	  }
	
	public void monter()
	{
		this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
	}
	public void monter(float vitesse)
	{
		this.moteurPrincipal.set(ControlMode.PercentOutput, vitesse);
	}
	
	  
	public double limiterPID(double val, double min, double max) 
	{
		return Math.max(min, Math.min(max, val));
	}
	  
	public void configurerMinirupteur()
	{	  
		this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		this.moteurPrincipal.configClearPositionOnLimitR(true, 0);
		  
		  //this.moteurPrincipal.configLimitSwitchDisableNeutralOnLOS(true, 10);
	}
	
	public boolean estBloquerParLimite() {
		  System.out.println("estBloquerParLimiteJambe() "+this.moteurPrincipal.getMotorOutputVoltage());
		  if(this.moteurPrincipal.getMotorOutputVoltage() > 0) {
			  return false;
		  }
		  return true;
	  }
	
	public boolean estArrive()
	{
		int distanceRestante = Math.abs((int)(lirePosition() - consigne));
		System.out.println("Distance restante jambe " + distanceRestante);
		if (distanceRestante < 500) return true;
		return false;
	}
	
}
