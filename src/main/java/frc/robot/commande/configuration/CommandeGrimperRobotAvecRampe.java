package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.CommandeDeplierJambe;

public class CommandeGrimperRobotAvecRampe extends CommandGroup{
	
    public CommandeGrimperRobotAvecRampe()
    {
    	System.out.println("new CommandeGrimperRobot()");
    	
    	//cuisse 3712 jambe 7923
    	// 3712 + 6000 parfaitement parallele au sol
    	
    	this.addParallel(new CommandeDeplierJambe(1700, ""));
    	this.addSequential(new CommandeDeplierCuisseSelonPid(1000));
    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeGrimperRobot.initialize()");
    }
    

}