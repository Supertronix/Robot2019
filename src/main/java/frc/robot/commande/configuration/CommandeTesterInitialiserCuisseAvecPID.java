package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterInitialiserCuisseAvecPID extends Command {
	

	 
	public CommandeTesterInitialiserCuisseAvecPID()
	{
		
    	requires(Robot.cuisse);
	}
	
	protected void execute(){
        System.out.println("CommandeTesterInitialiserCuisseAvecPID.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.cuisse.monter(-0.2f); 

    }

	@Override
	protected boolean isFinished() {
		return Robot.cuisse.estBloquerParLimite();
	}
}
