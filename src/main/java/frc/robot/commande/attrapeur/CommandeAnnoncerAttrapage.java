package frc.robot.commande.attrapeur;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Journal;

public class CommandeAnnoncerAttrapage extends CommandGroup{
	
    public CommandeAnnoncerAttrapage()
    {
    	Journal.ecrire(Journal.NIVEAU.NOTIFICATION,"new CommandeAnnoncerAttrapage()");
    	this.addParallel(new CommandeMonterGoupille());
    	this.addSequential(new CommandeAllumerTableTournante());  // automatiquement en parallele du premier
    }

    @Override
    protected void initialize()
    {
        Journal.ecrire(Journal.NIVEAU.NOTIFICATION, "CommandeAnnoncerAttrapage.initialize()");
    }
    

}