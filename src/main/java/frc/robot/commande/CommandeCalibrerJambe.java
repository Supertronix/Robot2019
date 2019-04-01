package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeCalibrerJambe extends Command {
	
	protected long debut = 0;
	protected long duree = 0;
	
	public CommandeCalibrerJambe()
	{
    	requires(Robot.jambe);
	}
	
    @Override
    protected void initialize()
    {
    	this.debut = System.currentTimeMillis();
    }	
    
	protected void execute(){
        System.out.println("CommandeCalibrerJambe.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        Robot.jambe.monter(-0.4f);
    }

	protected boolean estBloque;
	
	@Override
	protected boolean isFinished() {
		this.estBloque = Robot.jambe.estBloqueParLimite();
		if(this.estBloque) 
		{
			System.out.println("La cuisse estBloqueParLimite");
			//Robot.jambe.donnerConsignePID(0);
			Robot.jambe.activerCalibration();
			return this.estBloque;
		}
		this.duree = System.currentTimeMillis() - this.debut;
		if(this.duree > 5000)
		{
			System.out.println("Timeout du homing de la cuisse");
			Robot.jambe.initialiser();
			//Robot.jambe.donnerConsignePID(0);
			Robot.jambe.activerCalibration();
			return true;
		}
		return false;

	}
}
