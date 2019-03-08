package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeRoulerTreuil  extends Command{

	public CommandeRoulerTreuil()
	{
    	requires(Robot.treuil);
	}
    @Override
    protected void initialize()
    {
        System.out.println("CommandeRoulerTreuil.initialize()");
    }
	
	protected void execute(){
        System.out.println("CommandeRoulerTreuil.execute()");
        Robot.treuil.tourner(0.4f);
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
