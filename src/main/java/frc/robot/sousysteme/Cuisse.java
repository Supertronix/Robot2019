package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



// Aussi appelé Hanche par l'équipe	
public class Cuisse extends Subsystem implements RobotMap.Cuisse{
	
	public double POSITION_MIN = 0;
	public double POSITION_MAX = 6400; // 3700 sur robot competition	

	public int ERREUR_DISTANCE_PERMISE = 5;
	public int INVERSION = 1; // inutile depuis moteur.setIntverted(true) - le manuel etait non compatible avec limit switch
	public boolean INVERSE = true; // TODO inverser pour le robot competition
	  
	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected TalonSRX moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
	protected TalonSRX moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
	PIDController pidSecondaire;
	  //this.moteurSecondaire.setSensorPhase(false);
	  //this.moteurSecondaire.setInverted(false);
	  //this.moteurSecondaire.clearStickyFaults();
	  //this.moteurSecondaire.overrideLimitSwitchesEnable(true);	
	  //this.moteurPrincipal.setInverted(false);
	  //this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10); // 10 = timeout
	  //this.moteurPrincipal.configAllowableClosedloopError(0, 0, 10); // 10 = timeout
	  //this.moteurPrincipal.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0);
	  //this.moteurPrincipal.overrideLimitSwitchesEnable(true);
	
	public double PID_P = 0.5;
	public double PID_I = 0.00099;
	
	// valeurs video
	//public double PID_P = 0.5;
	//public double PID_I = 0.00099;

	
	// marche ralenti
	//public double PID_P = 0.1;
	//public double PID_I = 0.00005;
	
	
  public Cuisse()
  {
	  this.moteurPrincipal.configFactoryDefault();	  
	  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	  	  
	  this.moteurPrincipal.setInverted(INVERSE);
	  this.moteurPrincipal.setSensorPhase(true);
	  
	  //this.moteurPrincipal.setSelectedSensorPosition(0);
	  this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);	  
	  this.moteurPrincipal.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
	  this.moteurPrincipal.config_kP(0, PID_P, 10);
	  this.moteurPrincipal.config_kI(0, PID_I, 10);	  	  
	  
	  this.configurerMinirupteur();
	  
	  this.moteurSecondaire.configFactoryDefault();	  
	  this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
	  this.moteurSecondaire.setInverted(!INVERSE);
	  this.moteurSecondaire.setSensorPhase(true);
	  
	  ////EncodeurPrincipalSource sourceEncodeur = new EncodeurPrincipalSource(this.moteurPrincipal);
	  ////pidSecondaire = new PIDController(PID_P, PID_I, 0, sourceEncodeur, new TalonCible(this.moteurSecondaire));
	  // PIDController(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output)
	  // PIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output)
	  //this.moteurSecondaire.follow(this.moteurPrincipal);
	  
	  this.moteurSecondaire.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	  this.moteurSecondaire.configAllowableClosedloopError(0, 0, this.ERREUR_DISTANCE_PERMISE);
	  this.moteurSecondaire.config_kP(0, PID_P, 10);
	  this.moteurSecondaire.config_kI(0, PID_I, 10);
  }
  
  // la stratégie est soit de synchroniser les outputs de voltage avec la fonction synchroniser qu'on doit appeler à chaque itération
  // ou encore d'appliquer une simulation de pid identique ou tres similaire a celle du moteur principal
  public void synchroniser()
  {
	  Journal.ecrire("Cuisse.synchroniser()");
	  //this.moteurSecondaire.set(ControlMode.Position, this.moteurPrincipal.getSelectedSensorPosition());

	  // MARCHE PAS
	  //Journal.ecrire("Courant ENVOYE " + this.moteurPrincipal.getOutputCurrent());
	  //this.moteurSecondaire.set(ControlMode.Current, this.moteurPrincipal.getOutputCurrent());
	  //Journal.ecrire("Courant RECU " + this.moteurSecondaire.getOutputCurrent());
	  
	  //Journal.ecrire("Pourcent ENVOYE " + this.moteurPrincipal.getMotorOutputPercent());
	  this.moteurSecondaire.set(ControlMode.PercentOutput, (3)*this.moteurPrincipal.getMotorOutputPercent());
	  //Journal.ecrire("Pourcent RECU " + this.moteurSecondaire.getMotorOutputPercent());

	  // EXISTE PAS ENCORE ControlMode.VOLTAGE
	  //Journal.ecrire("Voltage ENVOYE " + this.moteurPrincipal.getMotorOutputVoltage());
	  //this.moteurSecondaire.set(ControlMode.VOLTAGE, this.moteurPrincipal.getMotorOutputVoltage());
	  //Journal.ecrire("Voltage RECU " + this.moteurSecondaire.getMotorOutputVoltage());
  }
  
  private class EncodeurPrincipalSource implements PIDSource
  {
	TalonSRX talon = null;
	@SuppressWarnings("unused")
	public EncodeurPrincipalSource(TalonSRX talon) 
	{
		  this.talon = talon;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return  PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return this.talon.getSelectedSensorPosition();
	}
  }
  private class TalonCible implements PIDOutput
  {
	  private TalonSRX talon;
	  public TalonCible(TalonSRX talon)
	  {
		  this.talon = talon;
	  }
	@Override
	public void pidWrite(double valeur) {
		this.talon.set(ControlMode.Position,valeur);
	}
	  
  }
  @Override
  public void initDefaultCommand() {}
  
  public void arreter()
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
  }
  public void monter()
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*0.1);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*0.1);
  }
  public void monter(float vitesse)
  {
	this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*vitesse);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
	
  }

  // Limit switches
  public void configurerMinirupteur()
  {	  
	  this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	  this.moteurPrincipal.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	  this.moteurPrincipal.configClearPositionOnLimitR(true, 0);
	  
	  //this.moteurPrincipal.configLimitSwitchDisableNeutralOnLOS(true, 10);
  }
  
  protected double position; 
  public double lirePosition() // max 3712
  {	  
	  //position = this.encodeur.getDistance();
	  this.position = this.moteurPrincipal.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  //position = this.moteurPrincipal.getSensorCollection().getQuadraturePosition(); // 742 (limit switch) a -2962
	  System.out.println("Cuisse.lirePosition() : " + INVERSION*this.position);
      SmartDashboard.putNumber("Position cuisse", INVERSION*this.position);	  
	  return INVERSION*this.position;
  }
    
  protected double consigne = 0;
  public void augmenterConsignePID(float increment) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  //Active close loop
		consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
		this.moteurPrincipal.set(ControlMode.Position, consigne);
		//this.moteurSecondaire.set(ControlMode.Position, consigne);
		////this.pidSecondaire.setSetpoint(consigne);
  }
  
  public void reduireConsignePID(float decrement) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
		this.moteurPrincipal.set(ControlMode.Position, consigne);
		//this.moteurSecondaire.set(ControlMode.Position, consigne);
		////this.pidSecondaire.setSetpoint(consigne);
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
		if (distanceRestante <= 50) return true;
		return false;
	}
 
	protected float positionCible = 0.0f;
	public float getPositionCible()
	{
        System.out.println("Cuisse.getPositionCible() : la position cible est " + this.positionCible);
		return this.positionCible;
	}
	public void positionner(float position)
	{
		this.positionCible = position;
		System.out.println("Cuisse.positionner() : la nouvelle position desiree est " + this.positionCible);
	}
	public void incrementerPosition(float incrementPosition)
	{
		this.positionCible = (float) (this.lirePosition() + incrementPosition);
		System.out.println("Cuisse.incrementerPosition() : la nouvelle position desirée est " + this.positionCible);
	}
	
	protected int distanceRestante;
	public boolean estArrivePositionCible()
	{
		this.distanceRestante = (int)(this.positionCible - lirePosition());
		System.out.println("Distance restante cuisse " + this.distanceRestante);
		if (Math.abs(this.distanceRestante) < 10) return true;
		if(this.position >= this.POSITION_MAX) return true;	
		return false;
	}


}
