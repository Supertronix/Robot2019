package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandePositionnerJambeManuellement extends Command{

	protected Manette manette;
	protected float position;
	protected String etiquette;
    public CommandePositionnerJambeManuellement(float position, String etiquette)
    {
     	System.out.println("new CommandePositionnerJambeManuellement() - " +  etiquette);
        requires(Robot.jambe);
        this.position = position;
        this.etiquette = etiquette;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandePositionnerJambeManuellement.initialize() - " + this.etiquette);
		Robot.jambe.annulerConsigne(); // arreter l'ancien pid
		this.manette = Manette.getInstance();
		Robot.jambe.incrementerPosition(position);
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandePositionnerJambeManuellement.execute() - " + this.etiquette);
        Robot.jambe.allerVersPositionCible();
        //Robot.jambe.synchroniser();
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandePositionnerJambeManuellement.isFinished() - "  + this.etiquette + " " + Robot.jambe.estArrivePositionCible() + " visant " + Robot.jambe.getPositionCible());
        if(Robot.jambe.estArrivePositionCible())
        {
        	Robot.jambe.arreter(); // arreter le mouvement manuel
        	Robot.jambe.fixerPosition();
        	return true; 
        }
        return false;
    }
}