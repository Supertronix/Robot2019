package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap.Attrapeur.Treuil;

public class CommandeDeroulerTreuil  extends Command{

	protected long debut;
	
	public CommandeDeroulerTreuil()
	{
    	requires(Robot.treuil);
	}
    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeroulerTreuil.initialize()");
        Robot.treuil.setNombrePas(15);
        this.debut = System.currentTimeMillis();
    }
	
	protected void execute(){
        System.out.println("CommandeDeroulerTreuil.execute()");
        Robot.treuil.tourner(-1f);
        //Robot.treuil.compterLesPas();
    }
	
	@Override
	protected boolean isFinished() {
        if(Robot.treuil.estArrive() || ((System.currentTimeMillis() - this.debut) > Treuil.DELAI_MAXIMUM))
        {
        	Robot.treuil.arreter();
        	return true;
        }
		return false;
	}

}
