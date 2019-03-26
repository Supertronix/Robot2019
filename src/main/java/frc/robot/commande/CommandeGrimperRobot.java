package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeGrimperRobot extends CommandGroup{
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeGrimperRobot()");
    	
    	//cuisse 3712 jambe 7923
    	// 3712 + 6000 parfaitement parallele au sol
    	
    	this.addParallel(new CommandeDeplierJambe(1700));
    	this.addSequential(new CommandeDeplierCuisse(1000));
    	
    	/*
    	this.addParallel(new CommandeDeplierJambe(1200));
    	this.addSequential(new CommandeDeplierCuisse(1700));
    	
    	this.addParallel(new CommandeDeplierJambe(700));
    	this.addSequential(new CommandeDeplierCuisse(300));
    	
    	this.addParallel(new CommandeDeplierJambe(600));
    	*/
    	//this.setTimeout(3);    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeGrimperRobot.initialize()");
    }
    

}