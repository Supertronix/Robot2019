package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeCalibrerJambe extends Command {
	
	public CommandeCalibrerJambe()
	{
    	requires(Robot.jambe);
	}
	
	protected void execute(){
        System.out.println("CommandeCalibrerJambe.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.jambe.monter(-0.2f);


    }

	@Override
	protected boolean isFinished() {
		
		return Robot.jambe.estBloquerParLimite();

	}
}
