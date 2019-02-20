package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeDeplierJambe extends Command{
	
	 protected float incrementAccumule = 0;
	 protected float increment = 0;
	
    public CommandeDeplierJambe(int increment)
    {
    	System.out.println("new CommandeDeplierJambe()");
    	this.increment = increment;        
    	requires(Robot.jambe);


    }
    
    protected void execute(){
        System.out.println("CommandeDeplierJambe.execute()");
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
    protected void initialize()
    {
        System.out.println("CommandeDeplierJambe.initialize()");
    }

	@Override
	protected boolean isFinished() {
		return Robot.jambe.estArrive();
	}
    

}