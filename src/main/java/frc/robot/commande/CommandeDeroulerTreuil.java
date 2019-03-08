package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeDeroulerTreuil  extends Command{

	public CommandeDeroulerTreuil()
	{
    	requires(Robot.treuil);
	}
    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeroulerTreuil.initialize()");
    }
	
	protected void execute(){
        System.out.println("CommandeDeroulerTreuil.execute()");
        Robot.treuil.tourner(-0.2f);
        int pas = Robot.treuil.compterLesPas();
    }
	
	@Override
	protected boolean isFinished() {
        if(Robot.treuil.estArrive())
        {
        	Robot.treuil.arreter();
        	return true;
        }
		return false;
	}

}
