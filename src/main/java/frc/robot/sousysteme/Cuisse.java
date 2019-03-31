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
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
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
			this.set(ControlMode.PercentOutput,0);
			this.getSensorCollection().setQuadraturePosition(0, 10);
			this.configNeutralDeadband(0.001, 10);
		}
		
		public TalonSupertronix(int numero, boolean inversion) {
			super(numero);
			this.configFactoryDefault();
			this.setNeutralMode(NeutralMode.Brake);	  	  
			this.setSensorPhase(true);
			this.set(ControlMode.PercentOutput,0);
			this.getSensorCollection().setQuadraturePosition(0, 10);
			this.setInverted(inversion);
		}
		
		public void activerEncodeur()
		{
			  //this.setSelectedSensorPosition(0);
			  this.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);	  
		}

		public void initialiserPID(double p, double i, double d)
		{
			  this.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
			  this.config_kP(0, p, 10);
			  this.config_kI(0, i, 10);	  	  
			  this.config_kD(0, d, 10);	
			  this.configClosedLoopPeriod(0, 1);
		}		
		
		public void activerMinirupteur()
		{
			  this.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
			  this.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
			  this.configClearPositionOnLimitR(true, 0);	
		}
		
		public void suivre(TalonSupertronix talon)
		{
			this.follow(talon); 
			//this.set(ControlMode.Follower, talon.getDeviceID());
		}
		
		public void synchroniser(TalonSupertronix talon)
		{
			
		}
		
		public void initialiserPIDAuxiliaire(double p, double i, double d)
		{
			this.configAllowableClosedloopError(1, 0,  this.ERREUR_DISTANCE_PERMISE);
			this.config_kP(1, p, 10);
			this.config_kI(1, i, 10);	  	  
			this.config_kD(1, d, 10);	
			this.configClosedLoopPeriod(1, 1);
			this.configClosedLoopPeakOutput(1, 1, 10);
		}		

	}
	
  public Cuisse()
  {
	  this.consignePrincipale = 0;
	  this.consigneSecondaire = 0;
		this.moteurPrincipalActif = true;
		this.moteurSecondaireActif = true;
		
		if(this.moteurPrincipalActif)
		{
		  this.moteurPrincipal = new TalonSupertronix(MOTEUR_PRINCIPAL, INVERSION_PRINCIPALE);		  
		  this.moteurPrincipal.initialiserPID(PID_P, PID_I, 0);
		  this.moteurPrincipal.activerMinirupteur();
		}
			  
		if(this.moteurSecondaireActif)
		{
		  this.moteurSecondaire = new TalonSupertronix(MOTEUR_SECONDAIRE, INVERSION_SECONDAIRE);
		  this.moteurSecondaire.activerEncodeur();
		  this.moteurSecondaire.initialiserPID(PID_P, PID_I, 0);
		  this.moteurSecondaire.activerMinirupteur();
		}
		
		if(this.moteurPrincipalActif && this.moteurSecondaireActif) 
		{
			this.moteurSecondaire.configRemoteFeedbackFilter(MOTEUR_SECONDAIRE, RemoteSensorSource.TalonSRX_SelectedSensor, 1, 10);
			this.moteurSecondaire.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor1, 10);
			this.moteurSecondaire.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
			this.moteurSecondaire.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor1, 10);
			this.moteurSecondaire.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
			
			this.moteurSecondaire.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 10);
			this.moteurSecondaire.configSelectedFeedbackCoefficient(0.5, 1, 10);
			this.moteurSecondaire.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 10);
			
			
			this.moteurSecondaire.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 10);
			this.moteurSecondaire.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 10);
			this.moteurSecondaire.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 10);
			
			this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 20, 10);
			
			this.moteurSecondaire.initialiserPIDAuxiliaire(2, 0, 4);
			this.moteurSecondaire.config_IntegralZone(1, 200, 10);
						
			this.moteurSecondaire.configAuxPIDPolarity(false, 10);
			this.moteurPrincipal.follow(this.moteurSecondaire, FollowerType.AuxOutput1);
			
		}
		//this.moteurPrincipal.getControlMode();

		
  }
  
  // la stratégie est soit de synchroniser les outputs de voltage avec la fonction synchroniser qu'on doit appeler à chaque itération
  // ou encore d'appliquer une simulation de pid identique ou tres similaire a celle du moteur principal
  public void synchroniser()
  {
	  Journal.ecrire("Cuisse.synchroniser()");
	  //this.moteurSecondaire.set(ControlMode.Position, this.moteurPrincipal.getSelectedSensorPosition());

	  // MARCHE PAS
	  //this.moteurPrincipal.set(ControlMode.Current, this.moteurSecondaire.getOutputCurrent());
	  //Journal.ecrire("Courant ENVOYE " + this.moteurSecondaire.getOutputCurrent());
	  //Journal.ecrire("Courant RECU " + this.moteurPrincipal.getOutputCurrent());
	  
	  double erreur = this.lirePositionSecondaire() - this.lirePositionPrincipale();
	  System.out.println("Erreur a corriger " + erreur);
	  //this.moteurPrincipal.set(ControlMode.Velocity, erreur/1000);
	  

	  
	  /*
	  this.moteurPrincipal.set(ControlMode.PercentOutput, this.moteurSecondaire.getMotorOutputPercent());
	  System.out.println("Pourcent ENVOYE " + this.moteurSecondaire.getMotorOutputPercent());
	  System.out.println("Pourcent RECU " + this.moteurPrincipal.getMotorOutputPercent());
	  */
	  
	  // EXISTE PAS ENCORE ControlMode.VOLTAGE	  
	  //Journal.ecrire("Voltage ENVOYE " + this.moteurSecondaire.getMotorOutputVoltage());
	  //this.moteurPrincipal.set(ControlMode.VOLTAGE, this.moteurSecondaire.getMotorOutputVoltage());
	  //Journal.ecrire("Voltage RECU " + this.moteurPrincipal.getMotorOutputVoltage());

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
	  System.out.println("Cuisse.lirePositionPrincipale() : " + INVERSION*this.position + " avec consigne de " + this.consignePrincipale);
      SmartDashboard.putNumber("Position cuisse C1", INVERSION*this.position);	  
	  return INVERSION*this.position;
  }
  
  protected double positionSecondaire; 
  public double lirePositionSecondaire()
  {
	  if(this.moteurSecondaireActif) this.positionSecondaire = this.moteurSecondaire.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  System.out.println("Cuisse.lirePositionSecondaire() : " + INVERSION*this.positionSecondaire + " avec consigne de " + this.consigneSecondaire);
      SmartDashboard.putNumber("Position cuisse C2", INVERSION*this.positionSecondaire);	  
	  return INVERSION*this.positionSecondaire;	  
  }
    
  protected double consignePrincipale = 0;
  protected double consigneSecondaire = 0;
  public void initialiser()
  {
		this.moteurSecondaire.set(ControlMode.PercentOutput, 0 , DemandType.ArbitraryFeedForward, 0);
		this.moteurPrincipal.set(ControlMode.PercentOutput, 0, DemandType.ArbitraryFeedForward, 0);		
		this.moteurPrincipal.getSensorCollection().setQuadraturePosition(0, 10);
		this.moteurPrincipal.getSensorCollection().setAnalogPosition(0, 10);
		this.moteurSecondaire.getSensorCollection().setQuadraturePosition(0, 10);
		this.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
  }
	public void donnerConsignePID(float consigne) {
		//consigne = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
		if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.Position, consigne, DemandType.AuxPID, 0);
		//if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.Position, -consigne);
  }
	
  public void augmenterConsignePID(float increment) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  //Active close loop
	  //if(this.moteurPrincipalActif) this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
	  //if(this.moteurPrincipalActif) System.out.println("Consigne moteur principal " + this.consignePrincipale);
	  //if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
	  
	  if(this.moteurSecondaireActif) this.consigneSecondaire = limiterPID(this.moteurSecondaire.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
	  if(this.moteurSecondaireActif) System.out.println("Consigne moteur secondaire " + this.consigneSecondaire);
	  if(this.moteurSecondaireActif) this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire, DemandType.AuxPID, 0);
	  
		////this.pidSecondaire.setSetpoint(consigne);
  }
  
  public void reduireConsignePID(float decrement) {
	  //double value = Calculateur.clamp(chariotMoteurPrincipal.getClosedLoopTarget(0) + 100, RobotMap.Chariot.CHARIOT_POSITION_BAS, RobotMap.Chariot.CHARIOT_POSITION_HAUT);

	  //if(this.moteurPrincipalActif) this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
	  //if(this.moteurPrincipalActif) System.out.println("Consigne moteur principal " + this.consignePrincipale);
	  //if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
	  
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
	  /*if(this.moteurPrincipalActif)System.out.println("estBloquerParLimiteCuisse() "+this.moteurPrincipal.getMotorOutputVoltage());
	  if(this.moteurPrincipalActif)
	  if(this.moteurPrincipal.getMotorOutputVoltage() > 0) {
		  return false;
	  }
	  return true;*/
	  System.out.println("estBloqueParLimite()" + this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed());
	  return this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed();
  }
  
  int distanceRestanteSelonConsigne;
	public boolean estArrive()
	{
		this.distanceRestanteSelonConsigne = Math.abs((int)(lirePositionSecondaire() - this.consigneSecondaire));
		System.out.println("Distance restante cuisse " + this.distanceRestanteSelonConsigne);
		if (distanceRestanteSelonConsigne <= 10) return true;
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
