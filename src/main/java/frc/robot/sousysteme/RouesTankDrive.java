package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class RouesTankDrive extends Roues{

  public static final int ROUE_GAUCHE = 17;
  public static final int ROUE_DROITE = 18;  

  protected VictorSP roueGauche = null;
  protected VictorSP roueDroite = null;
  protected DifferentialDrive tankDrive = null;

  public RouesTankDrive(){
    roueGauche = new VictorSP(ROUE_GAUCHE);
    roueDroite = new VictorSP(ROUE_DROITE);
    tankDrive = new DifferentialDrive(roueGauche, roueDroite);
  }

  @Override
  public void initDefaultCommand(){}
  
  public void conduire(double vitesseGauche, double vitesseDroite)
  {
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html#tankDrive-double-double-
		tankDrive.tankDrive(vitesseGauche, vitesseDroite);
	}
}