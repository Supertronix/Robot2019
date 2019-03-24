package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierJambeAvecMinirupteur extends Command{
	
	 protected float vitesse = 0;
	 protected float increment = 0;

    public CommandeTesterDeplierJambeAvecMinirupteur(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierJambe()");
    	requires(Robot.jambe);
    	Robot.jambe.positionner(0);
    	
    	this.vitesse = vitesse;        
    	this.increment = increment;
    	
    	// sécurité direction
    	if(this.increment < 0) this.vitesse = -(Math.abs(this.vitesse));
    	if(this.increment > 0) this.vitesse = (Math.abs(this.vitesse));
    	
        //Robot.jambe.POSITION_MAX = this.decalagePosition + 300;
    }
        
    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierJambe.initialize()");
        SmartDashboard.putNumber("Increment jambe ", this.increment);        
        System.out.println("initialize() : Increment jambe " + this.increment);
        
    	Robot.jambe.incrementerPosition(this.increment);
        Robot.jambe.monter(this.vitesse);
    }
    
    protected void execute(){
        System.out.println("");
        System.out.println("CommandeTesterDeplierJambe.execute()");
    }

    protected float distanceRestante;
    protected double position;
    protected double SEUIL = 10;
    protected double positionCible;
	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierJambe.isFinished() ");
        
        this.position = Robot.jambe.lirePosition();
        this.positionCible = Robot.jambe.getPositionCible();
        //this.distanceRestante = (float) (this.positionCible - this.position);
        //System.out.println("isFinished() : Distance restante = " + this.distanceRestante);
        if(this.increment > 0)
        {
	        System.out.println("("+this.position+" - "+this.SEUIL+") > "+this.positionCible+" " + ((this.position - this.SEUIL) > this.positionCible));
	        if((this.positionCible - this.SEUIL) < this.position){Robot.jambe.arreter(); return true;}
        }
        else
        {
	        System.out.println("("+this.position+" + "+this.SEUIL+") < "+this.positionCible+" " + ((this.position + this.SEUIL) < this.positionCible));
	        if((this.positionCible + this.SEUIL) > this.position){Robot.jambe.arreter(); return true;}        	
        }
        //System.out.println("isFinished() : this.positionReelle >= Robot.jambe.POSITION_MAX " + (this.positionReelle >= Robot.jambe.POSITION_MAX));
    	//if(this.positionReelle >= Robot.jambe.POSITION_MAX) {Robot.jambe.arreter(); return true;}	
    	return false;    
	}
    
}