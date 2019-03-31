package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.Robot;

public class CommandeDeplierCuisse extends Command{
	
	 protected float incrementAccumule = 0;
	 protected float increment = 0;

    public CommandeDeplierCuisse(int increment)
    {
    	System.out.println("new CommandeDeplierCuisse()");
    	this.increment = increment;        
    	requires(Robot.cuisse);
    }
    
    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierCuisse.initialize()");
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
    
    
    protected void execute(){
        System.out.println("CommandeDeplierCuisse.execute()");
    }

    // TODO trouver une maniere de valider la condition de sortie basee sur la non-progression car parfois il n'arrive jamais selon la batterie
	@Override
	protected boolean isFinished() {
		Journal.ecrire("CommandeDeplierCuisse.isFinished() " + Robot.cuisse.estArrive());  
        System.out.println("CommandeDeplierCuisse.isFinished() " + Robot.cuisse.estArrive());
        if(Robot.cuisse.estArrive()) Robot.cuisse.initialiser();// todo deplacer ce test
		return Robot.cuisse.estArrive();
	}
    

}