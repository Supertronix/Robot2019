package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeCalibrerJambe extends Command {
	
	public CommandeCalibrerJambe()
	{
    	requires(Robot.jambe);
	}
	
	protected void execute(){
        System.out.println("CommandeCalibrerJambe.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.jambe.monter(-0.4f);


    }

	protected boolean estBloque;
	
	@Override
	protected boolean isFinished() {
		this.estBloque = Robot.jambe.estBloquerParLimite();
		if(this.estBloque) Robot.jambe.donnerConsignePID(0);
		return this.estBloque;

	}
}
