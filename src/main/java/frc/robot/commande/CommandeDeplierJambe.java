package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.Robot;

public class CommandeDeplierJambe extends Command{
	
	 protected float increment = 0;
	
    public CommandeDeplierJambe(int increment)
    {
    	System.out.println("new CommandeDeplierJambe()");
    	this.increment = increment;        
    	requires(Robot.jambe);
    }
    
    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierJambe.initialize()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        SmartDashboard.putNumber("Increment jambe ", increment);        
        System.out.println("Increment jambe " + increment);
        //this.incrementAccumule += increment;
        //SmartDashboard.putNumber("Vitesse cuisse accumulee ", incrementAccumule);                
        //System.out.println("Vitesse cuisse accumulee " + incrementAccumule);
        
        if(this.increment > 0) {
            Robot.jambe.augmenterConsignePID(this.increment); 
        }
        else {
        	Robot.jambe.reduireConsignePID(Math.abs(increment));
        }
    }

    @Override    
    protected void execute(){
        System.out.println("CommandeDeplierJambe.execute()");
    }
    
    // TODO trouver une maniere de valider la condition de sortie basee sur la non-progression car parfois il n'arrive jamais selon la batterie
	@Override
	protected boolean isFinished() {
        Journal.ecrire("CommandeDeplierJambe.isFinished() " + Robot.jambe.estArrive());		
        System.out.println("CommandeDeplierJambe.isFinished() " + Robot.jambe.estArrive());		
		return Robot.jambe.estArrive();
	}
    

}