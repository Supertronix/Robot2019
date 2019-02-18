package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeTesterJambe extends Command{

    public CommandeTesterJambe()
    {
    	System.out.println("new CommandeTesterJambe()");
        requires(Robot.cuisse);
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterJambe.initialize()");
    }

    protected float vitesseAccumulee = 0;
    protected float vitesse = 0;
    
    @Override
    protected void execute(){
        System.out.println("CommandeTesterJambe.execute()");
    	this.vitesse = (float)ManetteConfiguration.getInstance().getAxeMainDroite().y / 5;
        Robot.jambe.monter(vitesse); 
        SmartDashboard.putNumber("Vitesse jambe", vitesse);        
        System.out.println("Vitesse jambe " + vitesse);
        this.vitesseAccumulee += vitesse;
        SmartDashboard.putNumber("Vitesse jambe accumulee ", vitesseAccumulee);                
        System.out.println("Vitesse jambe accumulee " + vitesseAccumulee);
    }

    @Override
    protected boolean isFinished(){
        //return !ManetteConfiguration.getInstance().savoirSiBoutonDroitPresse();
    	return false;
    }
}