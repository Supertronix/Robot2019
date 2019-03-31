package frc.robot.commande.configuration;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CommandeTesterDeplierCuisse extends Command{
	
	 protected double decalagePosition = 0;
	 protected float vitesse = 0;
	 protected float increment = 0;

    public CommandeTesterDeplierCuisse(float increment, float vitesse)
    {
    	System.out.println("new CommandeTesterDeplierCuisse()");
    	requires(Robot.cuisse);
    	
    	this.vitesse = vitesse;        
    	this.increment = increment;
    	
    	// sécurité direction
    	if(this.increment < 0) this.vitesse = -(Math.abs(this.vitesse));
    	if(this.increment > 0) this.vitesse = (Math.abs(this.vitesse));
    	
        //Robot.cuisse.POSITION_MAX = this.decalagePosition + 300;
    }
    
    protected double positionReelle;
    private double calculerPositionReelle()
    {
    	return Robot.cuisse.lirePositionPrincipale() - this.decalagePosition;
    }    
    
    @Override
    protected void initialize()
    {
        System.out.println("CommandeTesterDeplierCuisse.initialize()");
        SmartDashboard.putNumber("Increment cuisse ", this.increment);        
        System.out.println("initialize() : Increment cuisse " + this.increment);
    	Robot.cuisse.positionner(this.increment);        
        this.decalagePosition = Robot.cuisse.lirePositionPrincipale();
        System.out.println("initialize() : decalage position = " + this.decalagePosition + " position reelle = " + this.calculerPositionReelle());
        
        Robot.cuisse.monter(this.vitesse);
    }
    
    protected void execute(){
        System.out.println("CommandeTesterDeplierCuisse.execute()");
    }

    protected float distanceRestante;
    protected int SEUIL = 10;
	@Override
	protected boolean isFinished() {
        System.out.println("CommandeTesterDeplierCuisse.isFinished() ");
        
        this.positionReelle = this.calculerPositionReelle();         
        System.out.println("isFinished() : La position réelle est " + this.positionReelle);
        this.distanceRestante = (float) (Robot.cuisse.getPositionCible() - this.positionReelle);
        System.out.println("isFinished() : Distance restante = " + this.distanceRestante);
        
        if(this.increment > 0) System.out.println("isFinished() : (this.distanceRestante < this.SEUIL) " + (this.distanceRestante < this.SEUIL));
    	if(this.increment > 0) if (this.distanceRestante < this.SEUIL) {Robot.cuisse.arreter(); return true;}
    	
        if(this.increment < 0) System.out.println("isFinished() : (this.distanceRestante > -this.SEUIL) " + (this.distanceRestante > -this.SEUIL));
    	if(this.increment < 0) if (this.distanceRestante > -this.SEUIL) {Robot.cuisse.arreter(); return true;}
    	
        //System.out.println("isFinished() : this.positionReelle >= Robot.cuisse.POSITION_MAX " + (this.positionReelle >= Robot.cuisse.POSITION_MAX));
    	//if(this.positionReelle >= Robot.cuisse.POSITION_MAX) {Robot.cuisse.arreter(); return true;}	
    	return false;    
	}
    
}