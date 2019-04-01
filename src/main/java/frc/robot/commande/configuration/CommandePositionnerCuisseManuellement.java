package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandePositionnerCuisseManuellement extends Command{

	protected Manette manette;
	protected float position;
    public CommandePositionnerCuisseManuellement(float position)
    {
    	System.out.println("newCommandePositionnerCuisseManuellement()");
        requires(Robot.cuisse);
        this.position = position;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandePositionnerCuisseManuellement.initialize()");
		Robot.cuisse.annulerConsigne(); // arreter l'ancien pid
		this.manette = Manette.getInstance();
		Robot.cuisse.positionner(position);
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandePositionnerCuisseManuellement.execute()");
        Robot.cuisse.allerVersPositionCible();
        Robot.cuisse.synchroniser();
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandePositionnerCuisseManuellement.isFinished()");
        if(Robot.cuisse.estArrivePositionCible())
        {
        	Robot.cuisse.arreter(); // arreter le mouvement manuel
        	Robot.cuisse.fixerPosition();
        	return true; 
        }
        return false;
    }
}