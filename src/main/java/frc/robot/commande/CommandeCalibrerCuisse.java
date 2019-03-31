package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeCalibrerCuisse extends Command {
	
	protected long debut = 0;
	protected long duree = 0;
	
	public CommandeCalibrerCuisse()
	{
    	requires(Robot.cuisse);
	}
	
    @Override
    protected void initialize()
    {
    	this.debut = System.currentTimeMillis();
    }	
	
	protected void execute(){
        System.out.println("CommandeCalibrerCuisse.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.cuisse.monter(-0.3f); 

    }
	
	protected boolean estBloque;

	@Override
	protected boolean isFinished() {
		this.estBloque = Robot.cuisse.estBloqueParLimite();
		if(this.estBloque) 
		{
			Robot.cuisse.donnerConsignePID(0);
			Robot.cuisse.activerCalibration();
			return this.estBloque;
		}
		this.duree = System.currentTimeMillis() - this.debut;
		if(this.duree > 5000)
		{
			System.out.println("Timeout du homing de la cuisse");
			Robot.cuisse.initialiser();
			Robot.cuisse.donnerConsignePID(0);
			Robot.cuisse.activerCalibration();
			return true;
		}
		return false;
	}
}
