package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Journal;
import frc.robot.RobotMap;
import frc.robot.interaction.LecteurPositionTreuil;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
/*
 * Energie + le rentre davantage
 * Energie - le fait sortir
 * */
// Aussi appelé Winches par l'équipe	
public class Treuil extends Subsystem implements RobotMap.Attrapeur.Treuil{
	
	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected VictorSPX moteur = new VictorSPX(MOTEUR_TREUIL);
	protected LecteurPositionTreuil lecteurPosition;
			
  public Treuil()
  {
	  this.moteur.setNeutralMode(NeutralMode.Brake);	
	  this.lecteurPosition = new LecteurPositionTreuil();
  }
  
  public void initialiser()
  {
  }
  
  public double getPosition()
  {
	  return lecteurPosition.getPosition();
  }
  
  public boolean estRentre()
  {
	  return lecteurPosition.estAuMinimum();	  
  }
  
  public boolean estSorti()
  {
	  return lecteurPosition.estAuMaximum();	  
  }
    
  protected int nombrePas = 13;
  
  public void setNombrePas(int nombrePas)
  {
	  this.nombrePas = nombrePas;
  }
    
  @Override
  public void initDefaultCommand() {}
  /*
  public void tourner()
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.tourner()");	  
	this.moteur.set(ControlMode.PercentOutput, 0.1);
  }*/
  public void tourner(float vitesse)
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.tourner("+vitesse+")");
	Journal.ecrire("S'il rentre et n'est pas rentre " + (vitesse > 0 && !this.estRentre()));
	Journal.ecrire("S'il sort et n'est pas sorti " + (vitesse < 0 && !this.estSorti()));
	if((vitesse > 0 && !this.estRentre()) || (vitesse < 0 && !this.estSorti())) this.moteur.set(ControlMode.PercentOutput, vitesse);
	else this.arreter();
  }
  
  public void arreter()
  {
	Journal.ecrire(Journal.NIVEAU.DETAIL, "Treuil.arreter()");	  
	this.moteur.set(ControlMode.PercentOutput, 0);
	//this.compteurPas.initialiser();
  }
  
}
