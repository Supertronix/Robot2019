package frc.robot.interaction;

import edu.wpi.first.wpilibj.DigitalInput;

public class DetecteurEcoutilleAttrapee {
	
	protected DigitalInput detecteurDroit = null;
	protected DigitalInput detecteurGauche = null;
	
	public DetecteurEcoutilleAttrapee()
	{
		this.detecteurDroit = new DigitalInput(2);
		this.detecteurGauche = new DigitalInput(1);
	}

	public boolean estAttrapee()
	{
		return !this.detecteurDroit.get() && !this.detecteurGauche.get();
	}
	
	public boolean estDetecteCoteDroit()
	{
		return !this.detecteurDroit.get();
	}
	
	public boolean estDetecteCoteGauche()
	{
		return !this.detecteurGauche.get();
	}
	
}
