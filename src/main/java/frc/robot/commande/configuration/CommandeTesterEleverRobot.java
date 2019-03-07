package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeTesterEleverRobot extends CommandGroup{
	
    public CommandeTesterEleverRobot()
    {
    	System.out.println("new CommandeTesterEleverRobot()");
    	this.addParallel(new CommandeTesterJambe());
    	this.addSequential(new CommandeTesterCuisse()); // automatiquement en parallele du premier
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterEleverRobot.initialize()");
    }
    

}