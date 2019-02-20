package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeTesterInitialiserRobot extends CommandGroup{
	
    public CommandeTesterInitialiserRobot()
    {
    	System.out.println("new CommandeTesterInitialiserRobot()");
    	this.addParallel(new CommandeTesterInitialiserCuisse());
    	this.addParallel(new CommandeTesterInitialiserJambe());
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}