package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeGrimperRobot extends CommandGroup{
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeGrimperRobot()");
    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	//this.addParallel(new CommandeDeplierJambe(1000));
    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	
    	//cuisse 3712 jambe 7923
    	// 3712 + 6000 parfaitement parallele au sol
    	
    	//this.addParallel(new CommandeDeplierJambe(1700));
    	//this.addSequential(new CommandeDeplierCuisse(1000));

    	//this.addSequential(new CommandeDeplierCuisse(1000));
    	//this.addSequential(new CommandeDeplierJambe(1200));

    	this.addParallel(new CommandeDeplierJambe(1800));
    	this.addSequential(new CommandeDeplierCuisse(1100));
    	this.addSequential(new CommandeDeplierCuisse(800));
    	
    	//this.addParallel(new CommandeDeplierJambe(700));
    	//this.addSequential(new CommandeDeplierCuisse(500));
    	
    	//this.addSequential(new CommandeDeplierJambe(600));

    	//this.addParallel(new CommandeDeplierJambe(1200));
    	//this.addSequential(new CommandeDeplierCuisse(1400));
    	
    	
    	//this.setTimeout(3);    	
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
    

}