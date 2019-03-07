package frc.robot.commande;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeMonterGoupille extends Command{

    public CommandeMonterGoupille(){
        requires(Robot.attrapeur.cremaillere);
    }

    @Override
    protected void initialize(){
        System.out.println("CommandeMonterGoupille.initialize()");       
        Robot.attrapeur.cremaillere.monterGoupille();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeMonterGoupille.execute()");              
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}