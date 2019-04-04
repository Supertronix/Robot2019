package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class CommandeCalibrerRobot extends CommandGroup{
	
    public CommandeCalibrerRobot()
    {
    	System.out.println("new CommandeInitialiserRobot()");
    	//this.addParallel(new CommandeCalibrerJambe());
    	//this.addSequential(new CommandeCalibrerCuisse());
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}