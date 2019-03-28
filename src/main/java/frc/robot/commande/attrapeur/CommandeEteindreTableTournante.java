package frc.robot.commande.attrapeur;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeEteindreTableTournante extends Command{

    @SuppressWarnings("static-access")
	public CommandeEteindreTableTournante(){
        requires(Robot.attrapeur.tableTournante);
    }

    @SuppressWarnings("static-access")
	@Override
    protected void initialize(){
        System.out.println("CommandeEteindreTableTournante.initialize()");       
        Robot.attrapeur.tableTournante.eteindreTableTournante();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeEteindreTableTournante.execute()");              
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}