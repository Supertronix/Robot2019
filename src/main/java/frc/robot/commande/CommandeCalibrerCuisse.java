package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeCalibrerCuisse extends Command {
	

	 
	public CommandeCalibrerCuisse()
	{
		
    	requires(Robot.cuisse);
	}
	
	protected void execute(){
        System.out.println("CommandeCalibrerCuisse.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.cuisse.monter(-0.2f); 

    }
	
	protected boolean estBloque;

	@Override
	protected boolean isFinished() {
		this.estBloque = Robot.cuisse.estBloqueParLimite();
		if(this.estBloque) Robot.cuisse.donnerConsignePID(0);
		return this.estBloque;
	}
}
