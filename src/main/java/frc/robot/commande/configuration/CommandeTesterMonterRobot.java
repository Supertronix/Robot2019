package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeTesterMonterRobot extends CommandGroup{
	
    public CommandeTesterMonterRobot()
    {
    	System.out.println("new CommandeTesterMonterRobot()");
    	this.addParallel(new CommandeTesterChangementConsigneCuisseAvecPID(1000));
    	this.addParallel(new CommandeTesterChangementConsigneJambeAvecPID(1800));
    	//this.setTimeout(3);
    	//this.addParallel(new CommandeTesterInitialiserRobot());
    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}