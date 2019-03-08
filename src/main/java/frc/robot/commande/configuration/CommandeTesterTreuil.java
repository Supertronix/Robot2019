package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeTesterTreuil  extends Command{

	public CommandeTesterTreuil()
	{
    	requires(Robot.treuil);
	}
	
	protected void execute(){
        System.out.println("CommandeTesterTreuil.execute()");
        Robot.treuil.tourner(0.2f);
    }
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
