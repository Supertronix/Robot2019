package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeTesterTreuil  extends Command{

	public CommandeTesterTreuil()
	{
    	requires(Robot.treuil);
	}
    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterTreuil.initialize()");
        Robot.treuil.tourner(0.1f);
    }
	
	protected void execute(){
        System.out.println("CommandeTesterTreuil.execute()");
    }
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
