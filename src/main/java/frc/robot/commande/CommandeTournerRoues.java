package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeTournerRoues extends Command{

	float vitesse = 0;
	
    public CommandeTournerRoues(float vitesse)
    {
    	System.out.println("new CommandeTesterRoue()");
        requires(Robot.roues);
        this.vitesse = vitesse;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterRoue.initialize()");
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandeTesterRoue.execute()");
        //Robot.roues.conduireToutesDirections(vitesseAvantGauche, vitesseAvantDroite, vitesseArriereGauche, vitesseArriereDroite);
	    //float x1 = this.distance;
	    //float x2 = 0;
	    //public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) 
	    Robot.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
	    //Robot.roues.conduireToutesDirections(
	  	//      (0 + x1 + x2), 
	  	//      (0 - x1 - x2),	      
	  	//      (0 - x1 + x2), 
	  	//      (0 + x1 - x2) 
	  	//      );
    }

    @Override
    protected boolean isFinished(){
    	return false;
    }
}