package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Roues extends Subsystem implements RobotMap.Roues{

	public double limiter(double val) 
	{
		    return Math.max(-1, Math.min(1, val));
	}
	
    public Roues(){
    }

    @Override
    public void initDefaultCommand(){}
  
    public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) 
    {

    }
    public void conduire(double vitesseY, double vitesseX)
    {
    }
    public void tourner(double vitesse, double angle)
    {

    }
    
}