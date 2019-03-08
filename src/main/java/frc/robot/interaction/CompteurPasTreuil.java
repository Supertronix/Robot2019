package frc.robot.interaction;

import edu.wpi.first.wpilibj.DigitalInput;

public class CompteurPasTreuil {
	
	protected DigitalInput detecteur = null;
	
	public CompteurPasTreuil()
	{
		this.detecteur = new DigitalInput(3);
	}
	
	public boolean estDetecte()
	{
		return !this.detecteur.get();
	}
	
}
