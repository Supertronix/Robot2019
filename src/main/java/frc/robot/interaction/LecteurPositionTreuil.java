package frc.robot.interaction;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.RobotMap;

public class LecteurPositionTreuil implements RobotMap.Attrapeur.Treuil{
	
	protected AnalogInput sourceDetecteur;
	protected AnalogPotentiometer detecteur;
		
	public LecteurPositionTreuil()
	{
		this.sourceDetecteur = new AnalogInput(COMPTEUR_TREUIL);
		this.detecteur = new AnalogPotentiometer(sourceDetecteur, 360, DECALAGE_360); // 0-119
		// 0-360 : 230 rentre - 248 sorti - 227 sorti - 346 rentre
		//this.detecteur = new AnalogPotentiometer(sourceDetecteur); // entre 0 et 1 : 0.965 - 0.64		
	}
		
	public boolean estAuMaximum()
	{
		if(detecteur.get() <= (ANGLE_MINIMAL+SEUIL)) return true;
		return false;
	}

	public boolean estAuMinimum()
	{
		if(detecteur.get() >= (ANGLE_MAXIMAL-SEUIL)) return true;
		return false;
	}
	
	public double getPosition()
	{
		return detecteur.get();
	}
		
}
