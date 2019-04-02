package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.CommandeDeplierJambe;

public class CommandeGrimperRobotSaut extends CommandGroup{
	
    public CommandeGrimperRobotSaut()
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
    	
    	this.addParallel(new CommandeDeplierJambe(5000, ""));
    	this.addSequential(new CommandeDeplierCuisseSelonPid(3712));
    	this.addSequential(new CommandeDeplierJambe(2923, ""));
    	
    	//this.addSequential(new CommandeTournerRoues(1f));
    	
    	//this.setTimeout(3);    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}