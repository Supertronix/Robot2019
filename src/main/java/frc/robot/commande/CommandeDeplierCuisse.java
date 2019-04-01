package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeDeplierCuisse extends Command{

	protected Manette manette;
	protected float position;
    public CommandeDeplierCuisse(float position)
    {
    	System.out.println("new CommandeDeplierCuisse()");
        requires(Robot.cuisse);
        this.position = position;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierCuisse.initialize()");
		Robot.cuisse.annulerConsigne(); // arreter l'ancien pid
		this.manette = Manette.getInstance();
		Robot.cuisse.positionner(position);
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandeDeplierCuisse.execute()");
        Robot.cuisse.allerVersPositionCible();
        Robot.cuisse.synchroniser();
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandeDeplierCuisse.isFinished()");
        if(Robot.cuisse.estArrivePositionCible())
        {
        	Robot.cuisse.arreter(); // arreter le mouvement manuel
        	Robot.cuisse.fixerPosition();
        	return true; 
        }
        return false;
    }
}