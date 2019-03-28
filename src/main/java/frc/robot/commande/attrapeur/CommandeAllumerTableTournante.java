package frc.robot.commande.attrapeur;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeAllumerTableTournante extends Command{

    @SuppressWarnings("static-access")
	public CommandeAllumerTableTournante(){
        requires(Robot.attrapeur.tableTournante);
    }

    @SuppressWarnings("static-access")
	@Override
    protected void initialize(){
        System.out.println("CommandeAllumerTableTournante.initialize()");       
        Robot.attrapeur.tableTournante.allumerTableTournante();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeAllumerTableTournante.execute()");              
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}