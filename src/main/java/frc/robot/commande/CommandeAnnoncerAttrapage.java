package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.configuration.CommandeTesterCuisse;
import frc.robot.commande.configuration.CommandeTesterJambe;

public class CommandeAnnoncerAttrapage extends CommandGroup{
	
    public CommandeAnnoncerAttrapage()
    {
    	System.out.println("new CommandeAnnoncerAttrapage()");
    	this.addParallel(new CommandeMonterGoupille());
    	this.addSequential(new CommandeAllumerTableTournante());  // automatiquement en parallele du premier
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeAnnoncerAttrapage.initialize()");
    }
    

}