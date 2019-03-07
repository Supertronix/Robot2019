package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeEteindreTableTournante extends Command{

    public CommandeEteindreTableTournante(){
        requires(Robot.attrapeur.tableTournante);
    }

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