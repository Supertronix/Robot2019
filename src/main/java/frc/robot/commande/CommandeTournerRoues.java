package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeTournerRoues extends Command{

	float vitesse = 0;
	
    public CommandeTournerRoues(float vitesse)
    {
    	System.out.println("new CommandeTournerRoues()");
        requires(Robot.roues);
        this.vitesse = vitesse;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTournerRoues.initialize()");
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandeTournerRoues.execute()");
	    //Robot.roues.conduireToutesDirections(vitesse, -vitesse, -vitesse, vitesse);
    }

    @Override
    protected boolean isFinished(){
    	return false;
    }
    //Robot.roues.conduireToutesDirections(
  	//      (0 + x1 + x2), 
  	//      (0 - x1 - x2),	      
  	//      (0 - x1 + x2), 
  	//      (0 + x1 - x2) 
  	//      );
}