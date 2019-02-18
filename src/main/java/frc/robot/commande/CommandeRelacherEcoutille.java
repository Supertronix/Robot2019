package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeRelacherEcoutille extends Command{

    public CommandeRelacherEcoutille(){
        requires(Robot.attrapeur);
    }

    @Override
    protected void initialize(){
        System.out.println("CommandeRelacherEcoutille.initialize()");       
        Robot.attrapeur.relacherEcoutille();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeRelacherEcoutille.execute()");       
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}