package frc.robot.interaction;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.RobotMap;

public class LecteurPositionTreuil {
	
	protected AnalogInput sourceDetecteur;
	protected AnalogPotentiometer detecteur;
	
	protected int ANGLE_MINIMALE = 30;
	protected int ANGLE_MAXIMALE = 360;
	
	public LecteurPositionTreuil()
	{
		this.sourceDetecteur = new AnalogInput(RobotMap.Attrapeur.Treuil.COMPTEUR_TREUIL);
		this.detecteur = new AnalogPotentiometer(sourceDetecteur, ANGLE_MAXIMALE, ANGLE_MINIMALE);
	}
		
	public boolean estAuMinimum()
	{
		if(detecteur.get() == 0) return true;
		return false;
	}

	public boolean estAuMaximum()
	{
		if(detecteur.get() == 1) return true;
		return false;
	}
	
	public double getPosition()
	{
		return detecteur.get();
	}
		
}
