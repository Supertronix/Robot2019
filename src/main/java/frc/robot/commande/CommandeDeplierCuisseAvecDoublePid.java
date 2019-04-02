package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeDeplierCuisseAvecDoublePid extends Command{

	protected Manette manette;
	protected float increment;
	protected String etiquette;
    public CommandeDeplierCuisseAvecDoublePid(float increment, String etiquette)
    {
    	System.out.println("new CommandeDeplierCuisse() - " + this.etiquette);
        requires(Robot.cuisse);
        this.increment = increment;
        this.etiquette = etiquette;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierCuisse.initialize() - " + this.etiquette);
		Robot.cuisse.annulerConsigne(); // arreter l'ancien pid
        if(this.increment > 0) {
            Robot.jambe.augmenterConsignePID(this.increment); 
        }
        else {
        	Robot.jambe.reduireConsignePID(Math.abs(increment));
        }
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandeDeplierCuisse.execute() - "  + this.etiquette);
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandeDeplierCuisse.isFinished() - " + this.etiquette);
        if(Robot.cuisse.estArrive())
        {
        	return true; 
        }
        return false;
    }
}