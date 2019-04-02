package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.Robot;

public class CommandeDeplierJambe extends Command{
	
	 protected float increment = 0;
	protected String etiquette;
    public CommandeDeplierJambe(int increment, String etiquette)
    {
    	System.out.println("new CommandeDeplierJambe()");
    	this.increment = increment;        
    	requires(Robot.jambe);
    	this.etiquette = etiquette;
    }
    
    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierJambe.initialize() - " + this.etiquette);
        SmartDashboard.putNumber("Increment jambe ", increment);        
        System.out.println("Increment jambe " + increment);
        
        if(this.increment > 0) {
            Robot.jambe.augmenterConsignePID(this.increment); 
        }
        else {
        	Robot.jambe.reduireConsignePID(Math.abs(increment));
        }
    }

    @Override    
    protected void execute(){
        System.out.println("CommandeDeplierJambe.execute() - " + this.etiquette);
    }
    
    // TODO trouver une maniere de valider la condition de sortie basee sur la non-progression car parfois il n'arrive jamais selon la batterie
	@Override
	protected boolean isFinished() {
        Journal.ecrire("CommandeDeplierJambe.isFinished() " +  this.etiquette + " " + Robot.jambe.estArrive() + " " + Robot.jambe.getConsigne());		
        System.out.println("CommandeDeplierJambe.isFinished() " + this.etiquette + " " + Robot.jambe.estArrive() + " " + Robot.jambe.getConsigne());		
		return Robot.jambe.estArrive();
	}
    

}