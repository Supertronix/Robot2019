package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Journal;
import frc.robot.RobotMap;
import frc.robot.interaction.CompteurPasTreuil;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// Aussi appelé Winches par l'équipe	
public class Treuil extends Subsystem implements RobotMap.Attrapeur.Treuil{
	
	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected TalonSRX moteur = new TalonSRX(this.MOTEUR_TREUIL);
	//protected CompteurPasTreuil compteurPas;
			
  public Treuil()
  {
	  this.moteur.setNeutralMode(NeutralMode.Brake);	
	  //this.compteurPas = new CompteurPasTreuil();
  }
  
  //public int compterLesPas()
  //{
	  //this.compteurPas.detecter();
	  //Journal.ecrire(Journal.NIVEAU.DETAIL, "Nombre de pas : " + this.compteurPas.getPas());
	  //return this.compteurPas.getPas();
  //}
  
  protected int nombrePas = 13;
  
  public void setNombrePas(int nombrePas)
  {
	  this.nombrePas = nombrePas;
  }
  
  public boolean estArrive()
  {
	  //return (this.compteurPas.getPas() >= nombrePas); 
	  return true;
  }
  
  @Override
  public void initDefaultCommand() {}
  
  public void tourner()
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.tourner()");	  
	this.moteur.set(ControlMode.PercentOutput, 0.1);
  }
  public void tourner(float vitesse)
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.tourner("+vitesse+")");	  
	this.moteur.set(ControlMode.PercentOutput, vitesse);
  }
  
  public void arreter()
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.arreter()");	  
	this.moteur.set(ControlMode.PercentOutput, 0);
	//this.compteurPas.initialiser();
  }
  
}
