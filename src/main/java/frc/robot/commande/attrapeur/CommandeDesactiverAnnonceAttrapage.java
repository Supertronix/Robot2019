package frc.robot.commande.attrapeur;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Journal;

public class CommandeDesactiverAnnonceAttrapage extends CommandGroup{
	
    public CommandeDesactiverAnnonceAttrapage()
    {
    	Journal.ecrire(Journal.NIVEAU.NOTIFICATION,"new CommandeDesactiverAnnonceAttrapage()");
    	this.addParallel(new CommandeDescendreGoupille());
    	this.addSequential(new CommandeEteindreTableTournante());  // automatiquement en parallele du premier
    }

    @Override
    protected void initialize()
    {
        Journal.ecrire(Journal.NIVEAU.NOTIFICATION,"CommandeDesactiverAnnonceAttrapage.initialize()");
    }
    

}