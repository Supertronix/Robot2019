package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierCuisse extends Command{
	
	 protected double decalagePosition = 0;
	 protected float vitesse = 0;

    public CommandeTesterDeplierCuisse(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierCuisse()");
    	this.vitesse = vitesse;        
    	requires(Robot.cuisse);
    	Robot.cuisse.incrementerPosition(increment);
        SmartDashboard.putNumber("Increment cuisse ", increment);        
        System.out.println("Increment cuisse " + increment);
        this.decalagePosition = Robot.cuisse.lirePosition();
    }
    
    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierCuisse.initialize()");
        Robot.cuisse.monter(this.vitesse);
    }
    
    
    protected void execute(){
        System.out.println("CommandeTesterDeplierCuisse.execute()");
    }

    // TODO trouver une maniere de valider la condition de sortie basee sur la non-progression car parfois il n'arrive jamais selon la batterie
	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierCuisse.isFinished() " + Robot.cuisse.estArrivePositionCible());
        double positionReelle = Robot.cuisse.lirePosition() - this.decalagePosition;
        float distanceRestante = (float) (Robot.cuisse.getPositionCible() - positionReelle);
        System.out.println("Distance restante = " + distanceRestante);
        return (distanceRestante <= 0);
	}
    

}