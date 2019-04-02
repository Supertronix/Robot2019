package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.RobotMap;
import frc.robot.sousysteme.composant.TalonSupertronix;

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
	
	protected boolean modeConsigneSecondaire = false; // reste false - dangereux
	private boolean encodeurAuxiliaireActif = true;
	protected boolean modeSuiveux = false;
	protected boolean moteurPrincipalActif = true;
	protected boolean moteurSecondaireActif = true;
	
	/////////////////////////////////////////////////////////////////
	
	public int INVERSION = 1; // inutile depuis moteur.setIntverted(true) - le manuel etait non compatible avec limit switch
	public boolean INVERSION_PRINCIPALE = true; // TODO inverser pour le robot competition
	public boolean INVERSION_SECONDAIRE = false; // TODO inverser pour le robot competition
	  	
	public double POSITION_MIN = 0;
	public double POSITION_MAX = 20000; // 3700 sur robot competition	
	public double PID_P = 1;
	public double PID_I = 0.0001;//0.01;///0.00099;
	
	/////////////////////////////////////////////////////////////////
	
	protected TalonSupertronix moteurPrincipal = null;
	protected TalonSupertronix moteurSecondaire = null;
		
  public Cuisse()
  {
	  this.consignePrincipale = 0;
	  this.consigneSecondaire = 0;
		
		if(this.moteurPrincipalActif)
		{
		  this.moteurPrincipal = new TalonSupertronix(MOTEUR_SECONDAIRE, INVERSION_SECONDAIRE);		  
		  this.moteurPrincipal.activerEncodeur();
		  this.moteurPrincipal.initialiserPID(PID_P, PID_I, 0);
		  this.moteurPrincipal.activerMinirupteur();
		  this.moteurPrincipal.proteger();
		}
			  
		if(this.moteurSecondaireActif)
		{
		  this.moteurSecondaire = new TalonSupertronix(MOTEUR_PRINCIPAL, INVERSION_PRINCIPALE);
		  this.moteurSecondaire.activerEncodeur();
		  if(!this.modeSuiveux) this.moteurSecondaire.initialiserPID(PID_P, PID_I, 0);
		  this.moteurSecondaire.activerMinirupteur();
		  this.moteurSecondaire.proteger();
		}
		
		if(this.moteurPrincipalActif && this.moteurSecondaireActif) 
		{
			if(this.modeSuiveux) this.moteurSecondaire.follow(this.moteurPrincipal);			
		}
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
	  
	  //double erreur = this.lirePositionSecondaire() - this.lirePositionPrincipale();
	  //System.out.println("Erreur a corriger " + erreur);
	  //this.moteurPrincipal.set(ControlMode.Velocity, erreur/1000);
	  
		if(this.encodeurAuxiliaireActif)
		{
		  //System.out.println("Esclave : l'erreur est de " + this.moteurSecondaire.getClosedLoopError(0) + " sur une cible de " + this.moteurSecondaire.getClosedLoopTarget(0));
		  System.out.println("Auxiliaire (difference) : l'erreur est de " + this.moteurPrincipal.getClosedLoopError(1) + " sur une cible de " + this.moteurPrincipal.getClosedLoopTarget(1));
		  System.out.println("Master (moyenne): l'erreur est de " + this.moteurPrincipal.getClosedLoopError(0) + " sur une cible de " + this.moteurPrincipal.getClosedLoopTarget(0));
		}
	  
	  
	  //this.moteurSecondaire.set(ControlMode.PercentOutput, this.moteurPrincipal.getMotorOutputPercent());
	  //System.out.println("Pourcent ENVOYE " + this.moteurPrincipal.getMotorOutputPercent());
	  //System.out.println("Pourcent RECU " + this.moteurPrincipal.getMotorOutputPercent());	  
	  
	  // EXISTE PAS ENCORE ControlMode.VOLTAGE	  
	  //Journal.ecrire("Voltage ENVOYE " + this.moteurSecondaire.getMotorOutputVoltage());
	  //this.moteurPrincipal.set(ControlMode.VOLTAGE, this.moteurSecondaire.getMotorOutputVoltage());
	  //Journal.ecrire("Voltage RECU " + this.moteurPrincipal.getMotorOutputVoltage());

  }
  
  @Override
  public void initDefaultCommand() {}
  
  public void arreter()
  {
	System.out.println("Cuisse.arreter()");
	//if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
	if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
	this.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
  }
  public void monter(float vitesse) 
  {
	  System.out.println("Cuisse.monter(" + vitesse + ")");
	  //if(this.moteurSecondaireActif) System.out.println("Cuisse.moteurSecondaireActif = true");
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
	if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*vitesse);

	////this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
  }
  
  protected float position; 
  public float lirePositionPrincipale() // max 3712
  {	  
	  if(this.moteurPrincipalActif) this.position = this.moteurPrincipal.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  System.out.println("Cuisse.lirePositionPrincipale() : " + INVERSION*this.position);
      SmartDashboard.putNumber("Position cuisse C1", INVERSION*this.position);	  
	  return INVERSION*this.position;
  }
  
  protected float positionSecondaire; 
  public float lirePositionSecondaire()
  {
	  if(this.moteurSecondaireActif) this.positionSecondaire = this.moteurSecondaire.getSelectedSensorPosition(); // -748 (limit switch a 2964 
	  System.out.println("Cuisse.lirePositionSecondaire() : " + INVERSION*this.positionSecondaire);
      SmartDashboard.putNumber("Position cuisse C2", INVERSION*this.positionSecondaire);	  
      if(this.encodeurAuxiliaireActif) System.out.println("Valeur auxiliaire " + this.moteurSecondaire.getSelectedSensorPosition(1));
	  return INVERSION*this.positionSecondaire;	  
  }
    
  protected double consignePrincipale = 0;
  protected double consigneSecondaire = 0;
  public void initialiser()
  {
	  System.out.println("Cuisse.initialiser()");
	  if(this.moteurPrincipalActif)this.moteurPrincipal.getSensorCollection().setAnalogPosition(0, 10);
	  if(this.moteurSecondaireActif)this.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
  }
  public void annulerConsigne()
  {
	  System.out.println("Cuisse.annulerConsigne()");
	  this.moteurPrincipal.neutralOutput();
	  if(this.modeConsigneSecondaire) this.moteurSecondaire.neutralOutput();
  }
  boolean synchroManuelle = true;
  public void donnerConsignePID(float consigne) 
  {
	  System.out.println("Cuisse.donnerConsignePID("+consigne+")");
		this.consignePrincipale = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
	  if(this.moteurPrincipalActif) 
	  {
		  if(this.encodeurAuxiliaireActif) if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale, DemandType.AuxPID, 0);
		  else this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
      }
		  //if(this.synchroManuelle)
			  //this.moteurSecondaire.set(ControlMode.Position, this.consignePrincipale);
  }
  
  public void fixerPosition()
  {
	  System.out.println("Cuisse.fixerPosition()");
	  this.moteurPrincipal.set(ControlMode.Position, this.lirePositionPrincipale());
	  if(!this.modeSuiveux) this.moteurSecondaire.set(ControlMode.Position, this.lirePositionSecondaire());
  }
	
  public void augmenterConsignePID(float increment) {
	  System.out.println("Cuisse.augmenterConsignePID("+increment+")");
	  
	  if(this.moteurPrincipalActif) 
	  {
		  this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
		  System.out.println("Consigne moteur principal " + this.consignePrincipale);
		  if(this.encodeurAuxiliaireActif) if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale, DemandType.AuxPID, 0);
		  else this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
      }
  }
  
  public void reduireConsignePID(float decrement) {
	  System.out.println("Cuisse.reduireConsignePID("+decrement+")");
	  if(this.moteurPrincipalActif) 
	  {
		  this.consignePrincipale = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
		  System.out.println("Consigne moteur principal " + this.consignePrincipale);
		  if(this.encodeurAuxiliaireActif) if(this.moteurPrincipalActif) this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale, DemandType.AuxPID, 0);
		  else this.moteurPrincipal.set(ControlMode.Position, this.consignePrincipale);
      }
	  if(this.moteurSecondaireActif && this.modeConsigneSecondaire) this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire, DemandType.AuxPID, 0);
  }
  
  public double limiterPID(double val, double min, double max) 
  {
	  return Math.max(min, Math.min(max, val));
  }  
  
  public boolean estBloqueParLimite() 
  {
	  System.out.println("estBloqueParLimite()" + this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed());
	  return this.moteurPrincipal.getSensorCollection().isRevLimitSwitchClosed();
  }
  
  int distanceRestanteSelonConsigne;
  public boolean estArrive()
  {
		this.distanceRestanteSelonConsigne = Math.abs((int)(lirePositionPrincipale() - this.consignePrincipale));
		System.out.println("Distance restante cuisse " + this.distanceRestanteSelonConsigne);
		if (distanceRestanteSelonConsigne <= 40) return true;
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
	float facteur = 0.8996655518394649f;
	public void allerVersPositionCible()
	{
		System.out.println("Cuisse.allerVersPositionCible() - " + this.positionCible);
		float deltaPrincipal =  (this.positionCible - (float)this.lirePositionPrincipale());
		if(deltaPrincipal > 0) // this.monter(0.1f)
		{
			System.out.println("deltaPrincipal = " + deltaPrincipal);
			// ratio 0.8996655518394649
			// 0.5f, 0.8f //0.9f // 0.7f
			// 0.45f, 0.72f //0.81f //0.63f
			if(deltaPrincipal < 300)
			{
				if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, (deltaPrincipal/100)*0.3f); 
				if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, (deltaPrincipal/100)*0.3f*facteur); 				
			}
			else if(deltaPrincipal < 400)
			{
				if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.4f); 
				if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0.4f*facteur); 				
			}
			else
			{
				if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.6f); 
				if(this.moteurPrincipalActif)this.moteurPrincipal.set(ControlMode.PercentOutput, 0.6*facteur); 
			}
		}
		else // on ne replie jamais
		{
			
		}
		//float deltaSecondaire =  ((float)this.lirePositionSecondaire() - this.positionCible);//todo
	}
	protected int distanceRestante;
	public boolean estArrivePositionCible()
	{
		System.out.println("Cuisse.estArrivePositionCible()");
		this.distanceRestante = (int)(this.positionCible - lirePositionPrincipale());
		System.out.println("Distance restante cuisse " + this.distanceRestante);
		if (this.distanceRestante < 10) return true; // cibles toujours positives apres homing
		if(this.position >= this.POSITION_MAX) return true;	
		return false;
	}
	protected boolean estCalibre = false;
	public boolean estCalibre()
	{
		return this.estCalibre;
	}
	public void activerCalibration()
	{
		this.estCalibre = true;
		if(this.encodeurAuxiliaireActif) this.activerEncodeurAuxiliaire();
	}
	
	
	/////////////////////////////////////////////////////////////////
	
	  public void activerEncodeurAuxiliaire()
	  {
		  //this.annulerConsigne();
			this.moteurPrincipal.configRemoteFeedbackFilter(this.moteurSecondaire.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 1, 10);
			
			this.moteurPrincipal.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor1, 10);
			this.moteurPrincipal.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
			this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 10);
			this.moteurPrincipal.configSelectedFeedbackCoefficient(0.5, 0, 10);
			this.moteurPrincipal.initialiserPID(0.8, 0.00005, 0); //	public double PID_P = 0.1; public double PID_I = 0.00099;
			this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 10);
			this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 10);
			//this.moteurPrincipal.config_IntegralZone(1, 200, 10); // Integral Zone can be used to auto clear the integral accumulator if the sensor pos is too far from the target. This prevent unstable oscillation if the kI is too large. Value is in sensor units.
			
			this.moteurPrincipal.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor1, 10);
			this.moteurPrincipal.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
			this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 10);
			this.moteurPrincipal.configSelectedFeedbackCoefficient(-1, 1, 10);
			this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 10);
			this.moteurSecondaire.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 20, 10);
			this.moteurPrincipal.configAuxPIDPolarity(false, 10); 
			this.moteurPrincipal.initialiserPIDAuxiliaire(6, 0.1, 0); // 2 0 4
			
			this.moteurSecondaire.follow(this.moteurPrincipal, FollowerType.AuxOutput1);
			
	  }

}
