package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeArmerAttrapeur extends Command{

    public CommandeArmerAttrapeur(){
        requires(Robot.attrapeur);
    }

    @Override
    protected void initialize(){
        System.out.println("CommandeArmerAttrapeur.initialize()");       
        Robot.attrapeur.armer();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeArmerAttrapeur.execute()");              
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}