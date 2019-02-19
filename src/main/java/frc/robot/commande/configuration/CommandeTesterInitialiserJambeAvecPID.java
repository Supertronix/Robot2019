package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterInitialiserJambeAvecPID extends Command {
	
	public CommandeTesterInitialiserJambeAvecPID()
	{
    	requires(Robot.jambe);
	}
	
	protected void execute(){
        System.out.println("CommandeTesterInitialiserJambeAvecPID.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.jambe.monter(-0.2f);


    }

	@Override
	protected boolean isFinished() {
		
		return Robot.jambe.estBloquerParLimite();

	}
}
