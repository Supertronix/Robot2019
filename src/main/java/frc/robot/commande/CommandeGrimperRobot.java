package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commande.configuration.CommandeDeplierCuisseSelonPid;
import frc.robot.commande.configuration.CommandePositionnerCuisseManuellement;

public class CommandeGrimperRobot extends CommandGroup{
	
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeGrimperRobot()");
    	//this.setTimeout(3);    	
    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	//this.addParallel(new CommandeDeplierJambe(1000));
    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	
    	//cuisse 3712 jambe 7923
    	// 3712 + 6000 parfaitement parallele au sol
    	
    	//this.addParallel(new CommandeDeplierJambe(1700));
    	//this.addSequential(new CommandeDeplierCuisse(1000));

    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	//this.addSequential(new CommandeDeplierJambe(1200));
    	
    	
    	//this.addSequential(new CommandeDeplierCuisse(1200));
    	//this.addSequential(new CommandeDeplierJambe(1800));
    	
    	//this.addParallel(new CommandeDeplierJambe(700));
    	//this.addSequential(new CommandeDeplierCuisse(500));
    	
    	//this.addSequential(new CommandeDeplierJambe(600));

    	//this.addParallel(new CommandeDeplierJambe(1200));
    	//this.addSequential(new CommandeDeplierCuisse(1400));
    	
    	/* dernier test fonctionnel */
    	//this.addParallel(new CommandeDeplierJambe(1800));
    	//this.addSequential(new CommandeDeplierCuisseSelonPid(1100));
    	//this.addSequential(new CommandeDeplierCuisseSelonPid(800));
    	
    	//this.addParallel(new CommandePositionnerCuisseManuellement(600));
    	//this.addParallel(new CommandeDeplierJambe(1000));
    	    	
    	/*
    	this.addParallel(new CommandeDeplierJambe(2600, "JAMBE - 1"));
    	this.addSequential(new CommandePositionnerCuisseManuellement(600, "CUISSE 1"));
    	this.addParallel(new CommandeDeplierJambe(1300, "JAMBE - 2"));
    	this.addSequential(new CommandePositionnerCuisseManuellement(350, "CUISSE 2"));
    	*/
 
    	/*
    	this.addSequential(new CommandeGrimperJambeCuisse(new CommandeDeplierJambe(2600, "JAMBE - 1"), new CommandePositionnerCuisseManuellement(700, "CUISSE 1")));
    	this.addSequential(new CommandeGrimperJambeCuisse(new CommandeDeplierJambe(1300, "JAMBE - 1"), new CommandePositionnerCuisseManuellement(450, "CUISSE 1")));
    	*/
    	//this.addSequential(new CommandeGrimperJambeCuisse(new CommandeDeplierJambe(3000, "JAMBE - 1"), new CommandePositionnerCuisseManuellement(1000, "CUISSE 1")));
    	//this.addSequential(new CommandeDeplierJambe(400, "JAMBE - 1"));
    	this.addSequential(new CommandeGrimperJambeCuisse(new CommandeDeplierJambe(2000, "JAMBE - 1"), new CommandePositionnerCuisseManuellement(750, "CUISSE 1")));
    	this.addSequential(new CommandeGrimperJambeCuisse(new CommandeDeplierJambe(1000, "JAMBE - 1"), new CommandePositionnerCuisseManuellement(500, "CUISSE 1")));
    	this.addSequential(new CommandeDeplierJambe(300, "JAMBE - 1"));
    }
/* 2 x magique
 * 
 *     	this.addParallel(new CommandeDeplierJambe(1200));
    	this.addSequential(new CommandeDeplierCuisse(1700));
    	
    	this.addParallel(new CommandeDeplierJambe(700));
    	this.addSequential(new CommandeDeplierCuisse(300));
    	
    	this.addSequential(new CommandeDeplierJambe(600));

*/
    /* meilleur 
     * 
     *     	this.addParallel(new CommandeDeplierJambe(1200));
        	this.addSequential(new CommandeDeplierCuisse(1700));
        	
        	this.addParallel(new CommandeDeplierJambe(700));
        	this.addSequential(new CommandeDeplierCuisse(300));
        	
        	this.addSequential(new CommandeDeplierJambe(600));

        	this.addParallel(new CommandeDeplierJambe(1200));
        	this.addSequential(new CommandeDeplierCuisse(1700));
    */
    @Override
    protected void initialize()
    {
        System.out.println("CommandeGrimperRobot.initialize()");
    }
    
	public class CommandeGrimperJambeCuisse extends CommandGroup
	{
		protected Command commandeCuisse;
		protected Command commandeJambe;
		public CommandeGrimperJambeCuisse(Command commandeCuisse, Command commandeJambe)
		{
	        System.out.println("new CommandeGrimperJambeCuisse");
			this.commandeCuisse = commandeCuisse;
			this.commandeJambe = commandeJambe;
			this.addParallel(this.commandeJambe);
			this.addParallel(this.commandeCuisse);
		}
		
	    protected void initialize()
	    {
	        System.out.println("CommandeGrimperJambeCuisse.initialize()");
	    }		
	}


}