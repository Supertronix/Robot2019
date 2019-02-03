package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Roues{

    public static VictorSP roueGauche;
    public static VictorSP roueDroite;
    public static DifferentialDrive tankDrive;

    public Roues(){
    roueGauche = new VictorSP(17);
    roueDroite = new VictorSP(18);
    tankDrive = new DifferentialDrive(roueGauche, roueDroite);
    }

    public void conduire(double Y1, double Y2){
		tankDrive.tankDrive(Y1, Y2);
	}
}