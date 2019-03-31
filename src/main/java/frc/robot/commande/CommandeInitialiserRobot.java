package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.configuration.CommandeTesterCuisseAvecManette;
import frc.robot.interaction.Manette;

public class CommandeInitialiserRobot extends CommandGroup{
	
    public CommandeInitialiserRobot()
    {
    	System.out.println("new CommandeInitialiserRobot()");
    	this.addSequential(new CommandeCalibrerCuisse());
    	//this.addSequential(new CommandeCalibrerJambe());
    	//this.addSequential(new CommandeTesterCuisseAvecManette()); 
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}