package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierJambe extends Command{
	
	 protected float incrementAccumule = 0;
	 protected float vitesse = 0;
	
    public CommandeTesterDeplierJambe(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierJambe()");
    	requires(Robot.jambe);
    	this.vitesse = vitesse;        
    	Robot.jambe.incrementerPosition(increment);
        System.out.println("Increment jambe " + increment);
    }
    
    protected void execute(){
        System.out.println("CommandeTesterDeplierJambe.execute()");
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierJambe.initialize()");
    	Robot.jambe.monter(this.vitesse);
    }

	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierJambe.isFinished() " + Robot.jambe.estArrivePositionCible());		
		return Robot.jambe.estArrivePositionCible();
	}
    

}