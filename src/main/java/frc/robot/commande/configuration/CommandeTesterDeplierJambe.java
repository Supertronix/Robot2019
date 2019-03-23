package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierJambe extends Command{
	
	 protected double decalagePosition = 0;
	 protected float vitesse = 0;
	 protected float increment = 0;
	
    public CommandeTesterDeplierJambe(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierJambe()");
    	requires(Robot.jambe);
    	
    	this.vitesse = vitesse;        
    	this.increment = increment;
    	
    	// sécurité direction
    	if(this.increment < 0) this.vitesse = -(Math.abs(this.vitesse));
    	if(this.increment > 0) this.vitesse = (Math.abs(this.vitesse));
    	
        //Robot.jambe.POSITION_MAX = this.decalagePosition + 300;
    }
    
    
    protected double positionReelle;
    private double calculerPositionReelle()
    {
    	return Robot.cuisse.lirePosition() - this.decalagePosition;
    }    

    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierJambe.initialize()");
        SmartDashboard.putNumber("Increment jambe ", this.increment);        
        System.out.println("initialize() : Increment jambe " + this.increment);
    	//Robot.jambe.incrementerPosition(this.increment);
    	Robot.jambe.positionner(this.increment);        
        this.decalagePosition = Robot.jambe.lirePosition();
        System.out.println("initialize() : decalage position = " + this.decalagePosition + " position reelle = " + this.calculerPositionReelle());
        
        Robot.cuisse.monter(this.vitesse);
    }
    
    protected void execute(){
        System.out.println("CommandeTesterDeplierJambe.execute()");
    }

    protected float distanceRestante;
    protected int SEUIL = 10;
	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierJambe.isFinished() ");
        
        this.positionReelle = this.calculerPositionReelle();         
        System.out.println("isFinished() : La position réelle est " + this.positionReelle);
        this.distanceRestante = (float) (Robot.jambe.getPositionCible() - this.positionReelle);
        System.out.println("isFinished() : Distance restante = " + this.distanceRestante);
        
        if(this.increment > 0) System.out.println("isFinished() : (this.distanceRestante < this.SEUIL) " + (this.distanceRestante < this.SEUIL));
    	if(this.increment > 0) if (this.distanceRestante < this.SEUIL) {Robot.jambe.arreter(); return true;}
    	
        if(this.increment < 0) System.out.println("isFinished() : (this.distanceRestante > -this.SEUIL) " + (this.distanceRestante > -this.SEUIL));
    	if(this.increment < 0) if (this.distanceRestante > -this.SEUIL) {Robot.jambe.arreter(); return true;}
    	
        //System.out.println("isFinished() : this.positionReelle >= Robot.cuisse.POSITION_MAX " + (this.positionReelle >= Robot.cuisse.POSITION_MAX));
    	//if(this.positionReelle >= Robot.cuisse.POSITION_MAX) {Robot.cuisse.arreter(); return true;}	
    	return false;    
	}

}