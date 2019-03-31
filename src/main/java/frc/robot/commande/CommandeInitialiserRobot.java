package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeInitialiserRobot extends CommandGroup{
	
    public CommandeInitialiserRobot()
    {
    	System.out.println("new CommandeTesterInitialiserRobot()");
    	this.addParallel(new CommandeCalibrerCuisse());
    	this.addSequential(new CommandeCalibrerJambe());
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}