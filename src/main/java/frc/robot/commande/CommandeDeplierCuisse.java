package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeDeplierCuisse extends Command{
	
	 protected float incrementAccumule = 0;
	 protected float increment = 0;

	 
	
    public CommandeDeplierCuisse(int increment)
    {
    	System.out.println("new CommandeDeplierCuisse()");
    	this.increment = increment;        
    	requires(Robot.cuisse);


    }
    
    protected void execute(){
        System.out.println("CommandeDeplierCuisse.execute()");
    	//this.increment = (float)ManetteConfiguration.getInstance().getAxeMainGauche().y / 2;
        SmartDashboard.putNumber("Increment cuisse ", increment);        
        System.out.println("Increment cuisse " + increment);
        //this.incrementAccumule += increment;
        //SmartDashboard.putNumber("Vitesse cuisse accumulee ", incrementAccumule);                
        //System.out.println("Vitesse cuisse accumulee " + incrementAccumule);
        
        if(this.increment > 0) {
            Robot.cuisse.augmenterConsignePID(this.increment); 
        }
        else {
        	Robot.cuisse.reduireConsignePID(Math.abs(increment));
        }
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierCuisse.initialize()");
    }

	@Override
	protected boolean isFinished() {
		return Robot.cuisse.estArrive();
	}
    

}