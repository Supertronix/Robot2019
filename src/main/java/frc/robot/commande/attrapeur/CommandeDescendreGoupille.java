package frc.robot.commande.attrapeur;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CommandeDescendreGoupille extends Command{

    public CommandeDescendreGoupille(){
        requires(Robot.attrapeur.cremaillere);
    }

    @Override
    protected void initialize(){
        System.out.println("CommandeDescendreGoupille.initialize()");       
        Robot.attrapeur.cremaillere.descendreGoupille();
    }

    @Override
    protected void execute(){
        System.out.println("CommandeDescendreGoupille.execute()");              
    }

    @Override
    protected boolean isFinished(){
        return true;
    }
}