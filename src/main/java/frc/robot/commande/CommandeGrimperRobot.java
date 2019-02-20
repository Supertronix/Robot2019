package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeGrimperRobot extends CommandGroup{
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeTesterMonterRobot()");
    	
    	//this.addParallel(new CommandeDeplierCuisse(100));
    	//this.addParallel(new CommandeDeplierJambe(100));

    	this.addParallel(new CommandeDeplierCuisse(3000));
    	this.addSequential(new CommandeDeplierJambe(7923));
    	this.addSequential(new CommandeDeplierCuisse(712));
    	
    	this.addSequential(new CommandeTournerRoues(1f));
    	
    	//this.setTimeout(3);    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}