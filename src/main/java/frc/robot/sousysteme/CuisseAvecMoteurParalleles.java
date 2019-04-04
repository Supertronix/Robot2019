package frc.robot.sousysteme;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import frc.robot.sousysteme.composant.TalonSupertronix;

public class CuisseAvecMoteurParalleles extends Cuisse{

	public CuisseAvecMoteurParalleles()
	{
		super();				  
	}
	
	  public void configurerMoteurSecondaire() // polymorphique
	  {
		  this.moteurSecondaire.activerEncodeur();
		  this.moteurSecondaire.initialiserPID(PID_P, PID_I, 0);
		  this.moteurSecondaire.activerLimiteZero();			  
	  }
	
	  public void annulerConsigne()
	  {
		  super.annulerConsigne();
		  this.moteurSecondaire.neutralOutput();
	  }
	  
	  public void augmenterConsignePID(float increment) {
		  super.augmenterConsignePID(increment);
		  //if(this.moteurSecondaireActif) 
		  this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire);
	  }
	  public void reduireConsignePID(float decrement) {
		  super.reduireConsignePID(decrement);
		  this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire);
	  }
	  public void donnerConsignePID(float consigne) 
	  {
		  super.donnerConsignePID(consigne);
		  this.moteurSecondaire.set(ControlMode.Position, this.consigneSecondaire);
		  //if(this.synchroManuelle) //this.moteurSecondaire.set(ControlMode.Position, this.consignePrincipale);		  
	  }	
	  
}
