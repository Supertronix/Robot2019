package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeCalibrerCuisse extends Command {
	

	 
	public CommandeCalibrerCuisse()
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
		return Robot.cuisse.estBloqueParLimite();
	}
}
