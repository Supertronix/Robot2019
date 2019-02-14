package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Roues extends Subsystem implements RobotMap.Roues{


    public Roues(){
    }

    @Override
    public void initDefaultCommand(){}
  

    public void conduire(double vitesseY, double vitesseX)
    {
    }
    public void tourner(double vitesse, double angle)
    {

    }
    
}