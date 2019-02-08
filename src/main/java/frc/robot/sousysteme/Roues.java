package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Roues extends Subsystem implements RobotMap.Roues{

    public static VictorSP roueGauche;
    public static VictorSP roueDroite;
    public static DifferentialDrive tankDrive;

    public Roues(){
    roueGauche = new VictorSP(ROUE_GAUCHE);
    roueDroite = new VictorSP(ROUE_DROITE);
    tankDrive = new DifferentialDrive(roueGauche, roueDroite);
    }

    @Override
    public void initDefaultCommand(){}
  

    public void conduire(double Y1, double Y2){
		tankDrive.tankDrive(Y1, Y2);
	}
}