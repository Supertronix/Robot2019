package frc.robot.interaction;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Journal;
import frc.robot.RobotMap;

public class DetecteurEcoutilleAttrapee  implements RobotMap.Affichage{
	
	protected DigitalInput detecteurDroit = null;
	protected DigitalInput detecteurGauche = null;
	
	public DetecteurEcoutilleAttrapee()
	{
		this.detecteurDroit = new DigitalInput(this.SIGNAL_ECOUTILLE_DROIT);
		this.detecteurGauche = new DigitalInput(this.SIGNAL_ECOUTILLE_GAUCHE);
	}

	public boolean estAttrapee()
	{
		return !this.detecteurDroit.get() && !this.detecteurGauche.get();
	}
	
	public boolean estDetecteCoteDroit()
	{
		//Journal.ecrire("estDectecteCoteDroit() " + !this.detecteurDroit.get());
		return !this.detecteurDroit.get();
	}
	
	public boolean estDetecteCoteGauche()
	{
		//Journal.ecrire("estDectecteCoteGauche() " + !this.detecteurGauche.get());
		return !this.detecteurGauche.get();
	}
	
}
