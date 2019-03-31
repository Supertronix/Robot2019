package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeTesterCuisseAvecManette extends Command{

	protected Manette manette;
    public CommandeTesterCuisseAvecManette()
    {
    	System.out.println("new CommandeTesterCuisseAvecManette()");
        requires(Robot.cuisse);
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterCuisseAvecManette.initialize()");
		Robot.cuisse.annulerConsigne();
		this.manette = Manette.getInstance();
    }
    
    @Override
    protected void execute(){
    	//if(!Robot.cuisse.estCalibre()) return;
    	
    	System.out.println("==========================================");
    	System.out.println("AXE CUISSE : " + manette.getAxeMainGauche().y);
    	if(manette.getAxeMainGauche().y < -0.1) 
    	{
    		System.out.println("Le robot essaie de descendre " + manette.getAxeMainGauche().y);
    		Robot.cuisse.monter((float) manette.getAxeMainGauche().y);
    	}
    	else if(manette.getAxeMainGauche().y > 0.1) 
    	{
    		System.out.println("Le robot essaie de monter " + manette.getAxeMainGauche().y);
    		Robot.cuisse.monter((float) manette.getAxeMainGauche().y);
    	}
    	else
    	{
    		System.out.println("Le robot n'essaie pas de bouger");
    		Robot.cuisse.arreter();
    	}

    }

    @Override
    protected boolean isFinished(){
    	return false;
    }
}