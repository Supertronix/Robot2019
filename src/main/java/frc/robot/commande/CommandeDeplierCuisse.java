package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Journal;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteConfiguration;

public class CommandeDeplierCuisse extends Command{

	protected Manette manette;
	protected float increment;
	protected String etiquette;
    public CommandeDeplierCuisse(float increment, String etiquette)
    {
    	System.out.println("new CommandeDeplierCuisse() - " + this.etiquette);
        requires(Robot.cuisse);
        this.increment = increment;
        this.etiquette = etiquette;
    }

    @Override
    protected void initialize()
    {
        System.out.println("CommandeDeplierCuisse.initialize() - " + this.etiquette);
        SmartDashboard.putNumber("Increment cuisse ", increment);        
        System.out.println("Increment cuisse " + increment);
        
        if(this.increment > 0) {
            Robot.cuisse.augmenterConsignePID(this.increment); 
        }
        else {
        	Robot.cuisse.reduireConsignePID(Math.abs(increment));
        }
    }
    
    @Override
    protected void execute(){
        System.out.println("CommandeDeplierCuisse.execute() - "  + this.etiquette);
        System.out.println("La cuisse a pour consigne " + Robot.cuisse.getConsigne());
    }

    @Override
    protected boolean isFinished(){
        System.out.println("CommandeDeplierCuisse.isFinished() - " + this.etiquette);
        Journal.ecrire("CommandeDeplierJambe.isFinished() " +  this.etiquette + " " + Robot.cuisse.estArrive() + " " + Robot.cuisse.getConsigne());		
        System.out.println("CommandeDeplierJambe.isFinished() " + this.etiquette + " " + Robot.cuisse.estArrive() + " " + Robot.cuisse.getConsigne());		
		return Robot.cuisse.estArrive();
    }
}