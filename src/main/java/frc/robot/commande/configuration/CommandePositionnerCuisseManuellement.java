package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandePositionnerCuisseManuellement extends Command{

	protected Manette manette;
	protected float position;
	protected String etiquette;
    public CommandePositionnerCuisseManuellement(float position, String etiquette)
    {
     	System.out.println("new CommandePositionnerCuisseManuellement() - " +  etiquette);
        requires(Robot.cuisse);
        this.position = position;
        this.etiquette = etiquette;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandePositionnerCuisseManuellement.initialize() - " + this.etiquette);
		Robot.cuisse.annulerConsigne(); // arreter l'ancien pid
		this.manette = Manette.getInstance();
		Robot.cuisse.incrementerPosition(position);
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandePositionnerCuisseManuellement.execute() - " + this.etiquette);
        Robot.cuisse.allerVersPositionCible();
        Robot.cuisse.synchroniser();
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandePositionnerCuisseManuellement.isFinished() - "  + this.etiquette + " " + Robot.cuisse.estArrivePositionCible() + " visant " + Robot.cuisse.getPositionCible());
        if(Robot.cuisse.estArrivePositionCible())
        {
        	Robot.cuisse.arreter(); // arreter le mouvement manuel
        	Robot.cuisse.fixerPosition();
        	return true; 
        }
        return false;
    }
}