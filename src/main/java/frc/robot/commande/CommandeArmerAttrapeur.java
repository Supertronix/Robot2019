package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotControleur;

public class CommandeArmerAttrapeur extends Command{

    public CommandeArmerAttrapeur(){
        requires(RobotControleur.attrapeur);
    }

    @Override
    protected void initialize(){
        RobotControleur.attrapeur.armer();
    }

    @Override
    protected void execute(){
       
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}