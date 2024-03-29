package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeTesterCuisse extends Command{

    public CommandeTesterCuisse()
    {
    	System.out.println("new CommandeTesterCuisse()");
        requires(Robot.cuisse);
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterCuisse.initialize()");
    }

    protected float vitesseAccumulee = 0;
    protected float vitesse = 0;
    
    @Override
    protected void execute(){
        System.out.println("CommandeTesterCuisse.execute()");
    	this.vitesse = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.cuisse.monter(vitesse); 
        SmartDashboard.putNumber("Vitesse cuisse ", vitesse);        
        System.out.println("Vitesse cuisse " + vitesse);
        this.vitesseAccumulee += vitesse;
        SmartDashboard.putNumber("Vitesse cuisse accumulee ", vitesseAccumulee);                
        System.out.println("Vitesse cuisse accumulee " + vitesseAccumulee);
    }

    @Override
    protected boolean isFinished(){
        //return !ManetteConfiguration.getInstance().savoirSiBoutonGauchePresse();
    	return false;
    }
}