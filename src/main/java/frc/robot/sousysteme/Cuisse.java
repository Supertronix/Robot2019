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
	
	//private boolean encodeurAuxiliaireActif = false;
	protected boolean modeSuiveux = true;
	protected boolean moteurSecondaireActif = true;
	protected boolean synchroManuelle = false;
	//protected boolean modeConsigneSecondaire = false; // reste false - dangereux
	
	/////////////////////////////////////////////////////////////////
	
	public int INVERSION = 1; // inutile depuis moteur.setIntverted(true) - le manuel etait non compatible avec limit switch
	  	
	public double POSITION_MIN = -5000;
	public double POSITION_MAX = 5000; // 3700 sur robot competition	
	public double PID_P = 1.3;
	public double PID_I = 0.001;//0.01;///0.00099;
	
	/////////////////////////////////////////////////////////////////
	
	protected TalonSupertronix moteurPrincipal = null;
	protected TalonSupertronix moteurSecondaire = null;
		
  public Cuisse()
  {
	  this.consigne = 0;
	  this.consigneSecondaire = 0;
		
	  this.moteurPrincipal = new TalonSupertronix(MOTEUR_C2, INVERSION_C2);		  
	  this.moteurPrincipal.activerEncodeur();
	  this.moteurPrincipal.initialiserPID(PID_P, PID_I, 0);
	  this.moteurPrincipal.activerLimiteZero();
	  
	  if(this.moteurSecondaireActif) 
	  {
		  this.moteurSecondaire = new TalonSupertronix(MOTEUR_C1, INVERSION_C1);
		  this.configurerMoteurSecondaire();
	  }	  
  }
  
  public void configurerMoteurSecondaire() // polymorphique
  {
	  //if(this.modeSuiveux)
	  this.moteurSecondaire.suivre(this.moteurPrincipal);			
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
	this.moteurPrincipal.set(ControlMode.PercentOutput, 0.0);
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.0);
  }
  public void monter(float vitesse) 
  {
	System.out.println("Cuisse.monter(" + vitesse + ")");
	this.moteurPrincipal.set(ControlMode.PercentOutput, INVERSION*vitesse);
	if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, INVERSION*vitesse);
  }
  
  protected float position; 
  public float lirePositionPrincipale() // max 3712
  {	  
	  this.position = this.moteurPrincipal.getSelectedSensorPosition(); // -748 (limit switch a 2964 
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
      //if(this.encodeurAuxiliaireActif) System.out.println("Valeur auxiliaire " + this.moteurSecondaire.getSelectedSensorPosition(1));
	  return INVERSION*this.positionSecondaire;	  
  }
    
  protected double consigne = 0;
  protected double consigneSecondaire = 0;
  public void enregistrerPositionEncodeur(int position) 
  {
	  System.out.println("Cuisse.initialiser()");
	  this.moteurPrincipal.getSensorCollection().setQuadraturePosition(position, 10);
	  //if(this.moteurSecondaireActif)this.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
  }
  public void enregistrerPositionEncodeur() 
  {
	  System.out.println("Cuisse.initialiser()");
	  this.moteurPrincipal.getSensorCollection().setQuadraturePosition(this.moteurPrincipal.getSelectedSensorPosition(), 10);
	  //if(this.moteurSecondaireActif)this.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
  }
  public void initialiser() // initialise les senseurs a zero
  {
	  System.out.println("Cuisse.initialiser()");
	  //this.moteurPrincipal.getSensorCollection().setAnalogPosition(0, 10);
	  //if(this.moteurSecondaireActif)this.moteurSecondaire.getSensorCollection().setAnalogPosition(0, 10);
  }
  
	public double getConsigne()
	{
		return this.moteurPrincipal.getClosedLoopTarget();
		//return this.consigne;
	}

  public void annulerConsigne()
  {
	  System.out.println("Cuisse.annulerConsigne()");
	  this.moteurPrincipal.neutralOutput();
  }
  public void donnerConsignePID(float consigne) 
  {
	  System.out.println("Cuisse.donnerConsignePID("+consigne+")");
	  this.consigne = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
	  this.moteurPrincipal.set(ControlMode.Position, this.consigne);
	  //if(this.moteurSecondaireActif && this.modeConsigneSecondaire) this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire, DemandType.AuxPID, 0);
	  //if(this.synchroManuelle) //this.moteurSecondaire.set(ControlMode.Position, this.consignePrincipale);
  }
	
  public void augmenterConsignePID(float increment) {
	  System.out.println("Cuisse.augmenterConsignePID("+increment+")");
	  
	  //this.consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
	  this.consigne = limiterPID(this.consigne+ increment, POSITION_MIN, POSITION_MAX);
	  System.out.println("=====================================================");
	  System.out.println("Nouvelle consigne moteur principal " + this.consigne);
	  this.moteurPrincipal.set(ControlMode.Position, this.consigne);
  }
  
  public void reduireConsignePID(float decrement) {
	  System.out.println("Cuisse.reduireConsignePID("+decrement+")");
		  //this.consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
		  this.consigne = limiterPID(this.consigne - decrement, POSITION_MIN, POSITION_MAX);
		  System.out.println("Nouvelle consigne moteur principal " + this.consigne);
		  this.moteurPrincipal.set(ControlMode.Position, this.consigne);
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
		this.distanceRestanteSelonConsigne = Math.abs((int)(lirePositionPrincipale() - this.consigne));
		System.out.println("Distance restante cuisse " + this.distanceRestanteSelonConsigne);
		if (distanceRestanteSelonConsigne <= 40) return true;
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
	}
	
	
	/////////////////////////////////////////////////////////////////
  	/// Code pour controler manuellement le moteur sans PID ///
	/////////////////////////////////////////////////////////////////
  
  
  public void fixerPosition()
  {
  }
  
	public float getPositionCible()
	{
		return 0;
	}
	public void positionner(float position)
	{
	}
	public void incrementerPosition(float incrementPosition)
	{
	}
	
	public void allerVersPositionCible()
	{
	}
	public boolean estArrivePositionCible()
	{
		return false;
	}
	
}
