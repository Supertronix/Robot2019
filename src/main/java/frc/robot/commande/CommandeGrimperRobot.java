package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeGrimperRobot extends CommandGroup{
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeTesterMonterRobot()");
    	
    	//cuisse 3712 jambe 7923
    	// 3712 + 6000 parfaitement parallele au sol
    	//this.addParallel(new CommandeDeplierCuisse(3712));
    	//this.addSequential(new CommandeDeplierJambe(6000));
    	//this.addSequential(new CommandeDeplierJambe(1923));
    	
    	//this.addParallel(new CommandeDeplierCuisse(3712));
    	//this.addSequential(new CommandeDeplierJambe(6000));
    	//this.addSequential(new CommandeDeplierJambe(2923));
    	
    	this.addParallel(new CommandeDeplierJambe(5000));
    	this.addSequential(new CommandeDeplierCuisse(3712));
    	this.addSequential(new CommandeDeplierJambe(2923));
    	
    	//this.addSequential(new CommandeTournerRoues(1f));
    	
    	//this.setTimeout(3);    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}