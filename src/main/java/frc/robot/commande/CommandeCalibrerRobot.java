package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.configuration.CommandeTesterCuisseAvecManette;
import frc.robot.interaction.Manette;

public class CommandeCalibrerRobot extends CommandGroup{
	
    public CommandeCalibrerRobot()
    {
    	System.out.println("new CommandeInitialiserRobot()");
    	this.addParallel(new CommandeCalibrerJambe());
    	this.addSequential(new CommandeCalibrerCuisse());
    	//this.addSequential(new CommandeTesterCuisseAvecManette()); 
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}