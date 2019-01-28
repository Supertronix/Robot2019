package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotControleur;

public class CommandeRelacherEcoutille extends Command{

    public CommandeRelacherEcoutille(){
        requires(RobotControleur.attrapeur);
    }

    @Override
    protected void initialize(){
        RobotControleur.attrapeur.activerMoteur();
    }

    @Override
    protected void execute(){
       
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}