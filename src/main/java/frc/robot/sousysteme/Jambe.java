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

	public double POSITION_MIN = 0.0;
	public double POSITION_MAX = 7000.0; // 7923.0 pour le robot de competition	
	public int INVERSION = 1;
	public int ERREUR_DISTANCE_PERMISE = 5;
	
	public double PID_P = 0.6;
	public double PID_I = 0.00155; //0.00055;
	
	protected TalonSRX moteurPrincipal = null;
	//TEMPOprotected TalonSRX moteurSecondaire = null; 
	
	//protected Encoder encodeurMoteurPrincipal = new Encoder(ENCODEUR_MOTEUR_PRINCIPAL_A, ENCODEUR_MOTEUR_PRINCIPAL_B,  ENCODEUR_MOTEUR_PRINCIPAL_INVERSION, Encoder.EncodingType.k2X);
	
	public Jambe() {
		  this.moteurPrincipal = new TalonSRX(MOTEUR_PRINCIPAL);
		  this.moteurPrincipal.configFactoryDefault();
		  this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	
		  this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		  this.moteurPrincipal.configAllowableClosedloopError(0, 0, ERREUR_DISTANCE_PERMISE);
		  this.moteurPrincipal.setSensorPhase(true);
		  this.moteurPrincipal.config_kP(0, PID_P, 10);
		  this.moteurPrincipal.config_kI(0, PID_I, 10);		  		  
		  this.moteurPrincipal.setSelectedSensorPosition(0);
		  this.moteurPrincipal.setInverted(false);
		  this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.moteurPrincipal.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.moteurPrincipal.configClearPositionOnLimitR(true, 0);
		  		  
		  /*
		  this.moteurSecondaire = new TalonSRX(MOTEUR_SECONDAIRE);
		  this.moteurSecondaire.configFactoryDefault();
		  this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
		  this.moteurSecondaire.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		  this.moteurSecondaire.setSensorPhase(true);
		  //this.moteurSecondaire.configAllowableClosedloopError(0, 0, ERREUR_DISTANCE_PERMISE);
		  //this.moteurSecondaire.config_kP(0, PID_P, 10);
		  //this.moteurSecondaire.config_kI(0, PID_I, 10);		  		  
		  this.moteurSecondaire.setSelectedSensorPosition(0);
		  this.moteurSecondaire.setInverted(false);
		  this.moteurSecondaire.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.moteurSecondaire.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.moteurSecondaire.configClearPositionOnLimitR(true, 0);
		  
		  this.moteurSecondaire.follow(this.moteurPrincipal);
		   */
	}
	
	@Override
	protected void initDefaultCommand() {}
		
	protected double consigne = 0;
	public void donnerConsignePID(float consigne) {
			//consigne = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
			this.moteurPrincipal.set(ControlMode.Position, consigne);
			//this.moteurSecondaire.set(ControlMode.Position, consigne);
	  }

	public void augmenterConsignePID(float increment) {
		  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		  //Active close loop
			consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
			this.moteurPrincipal.set(ControlMode.Position, consigne);
			//this.moteurSecondaire.set(ControlMode.Position, consigne);

	  }
	  
	  public void reduireConsignePID(float decrement) {
		  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

		  consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
			this.moteurPrincipal.set(ControlMode.Position, consigne);
			//this.moteurSecondaire.set(ControlMode.Position, consigne);
	  }
	  
	  protected double position; 
	  public double lirePosition()
	  {	  
		  //position = this.encodeur.getDistance();
		  this.position = this.moteurPrincipal.getSelectedSensorPosition(); // 0-7923 
		  //position = this.moteurPrincipal.getSensorCollection().getQuadraturePosition(); 
		  System.out.println("Position jambe " + INVERSION*this.position);
	      SmartDashboard.putNumber("Position jambe", INVERSION*this.position);	  
		  return INVERSION*this.position;
	  }
	
	  public void arreter()
	  {
		this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
		//TEMPOthis.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
	  }
	  public void annulerConsigne()
	  {
		  //this.moteurSecondaire.set(ControlMode.Disabled, 0);
		  this.moteurPrincipal.neutralOutput();
		//TEMPOthis.moteurSecondaire.neutralOutput();
	  }	
	  public void monter(float vitesse)
	{
		this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*vitesse);
		//TEMPOthis.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
	}	
	  
	public double limiterPID(double val, double min, double max) 
	{
		return Math.max(min, Math.min(max, val));
	}
	  	
	public boolean estBloqueParLimite() {
		  /*System.out.println("estBloquerParLimiteJambe() "+this.moteurPrincipal.getMotorOutputVoltage());
		  if(this.moteurPrincipal.getMotorOutputVoltage() > 0) {
			  return false;
		  }
		  return true;
		  */
		  System.out.println("estBloqueParLimite()" + this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed());
		  return this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed();
	  }
	
	public boolean estArrive()
	{
		int distanceRestante = Math.abs((int)(lirePosition() - consigne));
		System.out.println("Distance restante jambe " + distanceRestante);
		if (distanceRestante < 50) return true;
		return false;
	}
	
	protected float positionCible = 0.0f;
	public float getPositionCible()
	{
		   System.out.println("Jambe.getPositionCible() : la position cible est " + this.positionCible);
		   return this.positionCible;
	}
	public void positionner(float position)
	{
		this.positionCible = position;
		// System.out.println("Jambe.positionner() : la nouvelle position desiree est " + this.positionCible);		
	}
	public void incrementerPosition(float incrementPosition)
	{
		this.positionCible += incrementPosition;
		// System.out.println("Jambe.incrementerPosition() : la nouvelle position desiree est " + this.positionCible);
	}
	
	protected int distanceRestante;
	public boolean estArrivePositionCible()
	{
		this.position = lirePosition();
		this.distanceRestante = (int)(this.positionCible - lirePosition());
		System.out.println("Distance restante jambe " + this.distanceRestante);
		if (Math.abs(this.distanceRestante) < 10) return true;
		if(this.position >= this.POSITION_MAX) return true;
		return false;
	}
	
	public void initialiser()
	{
		//if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0, DemandType.ArbitraryFeedForward, 0);		
		//if(this.moteurPrincipalActif)
		this.moteurPrincipal.getSensorCollection().setAnalogPosition(0, 10);
		//if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0 , DemandType.ArbitraryFeedForward, 0);
		//if(this.moteurSecondaireActif)
		//TEMPOthis.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
	}
	

	protected boolean estCalibre = false;
	public boolean estCalibre()
	{
		return this.estCalibre;
	}
	public void activerCalibration()
	{
		this.estCalibre = true;
	}
}
