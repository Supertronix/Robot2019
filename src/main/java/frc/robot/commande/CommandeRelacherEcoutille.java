package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeRelacherEcoutille extends Command{

    public CommandeRelacherEcoutille(){
        requires(Robot.attrapeur);
    }

    @Override
    protected void initialize(){
        Robot.attrapeur.relacherEcoutille();
    }

    @Override
    protected void execute(){
       
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}