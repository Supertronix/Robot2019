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
	public double POSITION_MAX = 20000; // 3700 sur robot competition	

	public int INVERSION = 1; // inutile depuis moteur.setIntverted(true) - le manuel etait non compatible avec limit switch
	public boolean INVERSION_PRINCIPALE = true; // TODO inverser pour le robot competition
	public boolean INVERSION_SECONDAIRE = false; // TODO inverser pour le robot competition
	  
	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected boolean moteurPrincipalActif = false;
	protected TalonSupertronix moteurPrincipal = null;
	protected boolean moteurSecondaireActif = false;
	protected TalonSupertronix moteurSecondaire = null;
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
	
	
	public class TalonSupertronix extends TalonSRX
	{
		public int ERREUR_DISTANCE_PERMISE = 5;

		public TalonSupertronix(int numero) {
			super(numero);
			this.configFactoryDefault();
			this.setNeutralMode(NeutralMode.Brake);	  	  
			this.setSensorPhase(true);
		}
		
		public TalonSupertronix(int numero, boolean inversion) {
			super(numero);
			this.configFactoryDefault();
			this.setNeutralMode(NeutralMode.Brake);	  	  
			this.setSensorPhase(true);
			this.setInverted(inversion);
		}
		
		public void activerEncodeur()
		{
			  //this.moteurPrincipal.setSelectedSensorPosition(0);
			  this.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);	  
		}

		////EncodeurPrincipalSource sourceEncodeur = new EncodeurPrincipalSource(this.moteurPrincipal);
		////pidSecondaire = new PIDController(PID_P, PID_I, 0, sourceEncodeur, new TalonCible(this.moteurSecondaire));
		// PIDController(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output)
		// PIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output)
		public void initialiserPID(double p, double i, double d)
		{
			  this.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
			  this.config_kP(0, p, 10);
			  this.config_kI(0, i, 10);	  	  
			  this.config_kD(0, d, 10);	  	  
		}		
		
		public void activerMinirupteur()
		{
			  this.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
			  this.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
			  this.configClearPositionOnLimitR(true, 0);	
		}
	}
	
  public Cuisse()
  {
		this.moteurPrincipalActif = true;
		this.moteurSecondaireActif = true;
		
		if(this.moteurPrincipalActif)
		{
		  this.moteurPrincipal = new TalonSupertronix(MOTEUR_PRINCIPAL, INVERSION_PRINCIPALE);
		  //this.moteurPrincipal.configFactoryDefault();	  
		  //this.moteurPrincipal.setNeutralMode(NeutralMode.Brake);	  	  
		  //this.moteurPrincipal.setSensorPhase(true);
		  //this.moteurPrincipal.setInverted(INVERSION_PRINCIPALE);
		  
		  this.moteurPrincipal.activerEncodeur();
		  //this.moteurPrincipal.setSelectedSensorPosition(0);
		  //this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);	  
		  //this.moteurPrincipal.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
		  
		  this.moteurPrincipal.initialiserPID(PID_P, PID_I, 0);
		  //this.moteurPrincipal.config_kP(0, PID_P, 10);
		  //this.moteurPrincipal.config_kI(0, PID_I, 10);	  	  
		  
		  this.moteurPrincipal.activerMinirupteur();
		  //this.moteurPrincipal.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  //this.moteurPrincipal.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  //this.moteurPrincipal.configClearPositionOnLimitR(true, 0);

		}
			  
		if(this.moteurSecondaireActif)
		{
		  this.moteurSecondaire = new TalonSupertronix(MOTEUR_SECONDAIRE, INVERSION_SECONDAIRE);
		  //this.moteurSecondaire.configFactoryDefault();	  
		  //this.moteurSecondaire.setNeutralMode(NeutralMode.Brake);
		  //this.moteurSecondaire.setSensorPhase(true);
		  //this.moteurSecondaire.setInverted(INVERSION_SECONDAIRE);
		  		  
		  this.moteurSecondaire.activerEncodeur();
		  //this.moteurSecondaire.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		  //this.moteurSecondaire.configAllowableClosedloopError(0, 0, this.ERREUR_DISTANCE_PERMISE);
		  
		  this.moteurSecondaire.initialiserPID(PID_P, PID_I, 0);
		  //this.moteurSecondaire.config_kP(0, PID_P, 10);
		  //this.moteurSecondaire.config_kI(0, PID_I, 10);
		  
		  this.moteurSecondaire.activerMinirupteur();
		  //this.moteurSecondaire.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  //this.moteurSecondaire.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  //this.moteurSecondaire.configClearPositionOnLimitR(true, 0);
		  
		  //this.moteurSecondaire.follow(this.moteurPrincipal);
		}
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
	  ////this.moteurSecondaire.set(ControlMode.PercentOutput, (3)*this.moteurPrincipal.getMotorOutputPercent());
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
	  @SuppressWarnings("unused")
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
	if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
  }
  public void monter()
  {
	if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*0.1);
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*0.1);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*0.1);
  }
  public void monter(float vitesse)
  {
	if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*vitesse);
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
	////this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
  }
  
  protected double position; 
  public double lirePositionPrincipale() // max 3712
  {	  
	  //position = this.encodeur.getDistance();
	  if(this.moteurPrincipalActif) this.position = this.moteurPrincipal.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  //position = this.moteurPrincipal.getSensorCollection().getQuadraturePosition(); // 742 (limit switch) a -2962
	  System.out.println("Cuisse.lirePosition() : " + INVERSION*this.position);
      SmartDashboard.putNumber("Position cuisse", INVERSION*this.position);	  
	  return INVERSION*this.position;
  }
  
  protected double positionSecondaire; 
  public double lirePositionSecondaire()
  {
	  if(this.moteurSecondaireActif) this.positionSecondaire = this.moteurSecondaire.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  System.out.println("Cuisse.lirePositionSecondaire() : " + INVERSION*this.positionSecondaire);
      SmartDashboard.putNumber("Position cuisse", INVERSION*this.positionSecondaire);	  
	  return INVERSION*this.positionSecondaire;	  
  }
    
  protected double consignePrincipale = 0;
  protected double consigneSecondaire = 0;
	public void donnerConsignePID(float consigne) {
		//consigne = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
		if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.Position, consigne);
		if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.Position, -consigne);
  }
	
  public void augmenterConsignePID(float increment) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  //Active close loop
	  if(this.moteurPrincipalActif) this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
	  if(this.moteurPrincipalActif) System.out.println("Consigne moteur principal " + this.consignePrincipale);
	  if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
	  
	  if(this.moteurSecondaireActif) this.consigneSecondaire = limiterPID(this.moteurSecondaire.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
	  if(this.moteurSecondaireActif) System.out.println("Consigne moteur secondaire " + this.consigneSecondaire);
	  if(this.moteurSecondaireActif) this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire);
	  
		////this.pidSecondaire.setSetpoint(consigne);
  }
  
  public void reduireConsignePID(float decrement) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  if(this.moteurPrincipalActif) this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
	  if(this.moteurPrincipalActif) System.out.println("Consigne moteur principal " + this.consignePrincipale);
	  if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
	  
	  if(this.moteurSecondaireActif) this.consigneSecondaire = limiterPID(this.moteurSecondaire.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
	  if(this.moteurSecondaireActif) System.out.println("Consigne moteur secondaire " + this.consigneSecondaire);
	  if(this.moteurSecondaireActif) this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire);
	  
		////this.pidSecondaire.setSetpoint(consigne);
  }
  
  public double limiterPID(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}
  
  
  public boolean estBloqueParLimite() {
	  if(this.moteurPrincipalActif)System.out.println("estBloquerParLimiteCuisse() "+this.moteurPrincipal.getMotorOutputVoltage());
	  if(this.moteurPrincipalActif)
	  if(this.moteurPrincipal.getMotorOutputVoltage() > 0) {
		  return false;
	  }
	  return true;
  }
  
  int distanceRestanteSelonConsigne;
	public boolean estArrive()
	{
		this.distanceRestanteSelonConsigne = Math.abs((int)(lirePositionPrincipale() - this.consignePrincipale));
		System.out.println("Distance restante cuisse " + this.distanceRestanteSelonConsigne);
		if (distanceRestanteSelonConsigne <= 50) return true;
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
		this.positionCible = (float) (this.lirePositionPrincipale() + incrementPosition);
		System.out.println("Cuisse.incrementerPosition() : la nouvelle position desirée est " + this.positionCible);
	}
	
	protected int distanceRestante;
	public boolean estArrivePositionCible()
	{
		this.distanceRestante = (int)(this.positionCible - lirePositionPrincipale());
		System.out.println("Distance restante cuisse " + this.distanceRestante);
		if (Math.abs(this.distanceRestante) < 10) return true;
		if(this.position >= this.POSITION_MAX) return true;	
		return false;
	}


}
