package frc.robot.commande;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandeGrimperRobot extends CommandGroup{
	
    public CommandeGrimperRobot()
    {
    	System.out.println("new CommandeTesterMonterRobot()");
    	//this.addParallel(new CommandeTesterChangementConsigneCuisseAvecPID(450));
    	//this.addParallel(new CommandeTesterChangementConsigneJambeAvecPID(950));
    	
    	//this.addParallel(new CommandeTesterChangementConsigneCuisseAvecPID(900));
    	//this.addParallel(new CommandeTesterChangementConsigneJambeAvecPID(1650));
    	
    	//this.addParallel(new CommandeTesterChangementConsigneCuisseAvecPID((int) (1800*1.5)));
    	//this.addParallel(new CommandeTesterChangementConsigneJambeAvecPID((int) (3300*1.5)));
    	
    	this.addParallel(new CommandeDeplierCuisse(3000));
    	this.addParallel(new CommandeDeplierJambe(7923));

    	//this.addSequential(new CommandeTesterChangementConsigneCuisse(712));
    	
    	
    	this.addSequential(new CommandeTournerRoues(1f));
    	
    	//this.setTimeout(3);
    	//this.addParallel(new CommandeTesterInitialiserRobot());
    	
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterInitialiserRobot.initialize()");
    }
    

}