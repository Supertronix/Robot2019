package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierCuisseAvecMinirupteur extends Command{
	
	 protected float vitesse = 0;
	 protected float increment = 0;

    public CommandeTesterDeplierCuisseAvecMinirupteur(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierCuisse()");
    	requires(Robot.cuisse);
    	Robot.cuisse.positionner(0);
    	
    	this.vitesse = vitesse;        
    	this.increment = increment;
    	
    	// sécurité direction
    	if(this.increment < 0) this.vitesse = -(Math.abs(this.vitesse));
    	if(this.increment > 0) this.vitesse = (Math.abs(this.vitesse));
    	
        //Robot.cuisse.POSITION_MAX = this.decalagePosition + 300;
    }
        
    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierCuisse.initialize()");
        SmartDashboard.putNumber("Increment cuisse ", this.increment);        
        System.out.println("initialize() : Increment cuisse " + this.increment);
        
    	Robot.cuisse.incrementerPosition(this.increment);
        Robot.cuisse.monter(this.vitesse);
    }
    
    protected void execute(){
        System.out.println("");
        System.out.println("CommandeTesterDeplierCuisse.execute()");
    }

    protected float distanceRestante;
    protected double position;
    protected double SEUIL = 10;
    protected double positionCible;
	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierCuisse.isFinished() ");
        
        this.position = Robot.cuisse.lirePositionPrincipale();
        this.positionCible = Robot.cuisse.getPositionCible();
        //this.distanceRestante = (float) (this.positionCible - this.position);
        //System.out.println("isFinished() : Distance restante = " + this.distanceRestante);
        if(this.increment > 0)
        {
	        System.out.println("("+this.position+" - "+this.SEUIL+") > "+this.positionCible+" " + ((this.position - this.SEUIL) > this.positionCible));
	        if((this.positionCible - this.SEUIL) < this.position){Robot.cuisse.arreter(); return true;}
        }
        else
        {
	        System.out.println("("+this.position+" + "+this.SEUIL+") < "+this.positionCible+" " + ((this.position + this.SEUIL) < this.positionCible));
	        if((this.positionCible + this.SEUIL) > this.position){Robot.cuisse.arreter(); return true;}        	
        }
        //System.out.println("isFinished() : this.positionReelle >= Robot.cuisse.POSITION_MAX " + (this.positionReelle >= Robot.cuisse.POSITION_MAX));
    	//if(this.positionReelle >= Robot.cuisse.POSITION_MAX) {Robot.cuisse.arreter(); return true;}	
    	return false;    
	}

    
}