package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;

public class RouesMecanum extends Roues{

// https://www.vexrobotics.com/mecanum-wheels.html
// https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599704-driving-a-robot-using-mecanum-drive
// http://files.andymark.com/PDFs/MecanumWheelTutorial.pdf
// http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html

// Drive base diagram selon first : 

//  \\_______/
//  \\ |   | /
//     |   |
//   /_|___|_\\
//  /         \\

  protected VictorSP roueGaucheAvant = null;
  protected VictorSP roueDroiteAvant = null;
  protected VictorSP roueGaucheArriere = null;
  protected VictorSP roueDroiteArriere = null;

  protected MecanumDrive conduite = null; 

  public RouesMecanum()
  {
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/VictorSP.html
    this.roueGaucheAvant = new VictorSP(ROUE_GAUCHE_AVANT);
    this.roueDroiteAvant = new VictorSP(ROUE_DROITE_AVANT);
    this.roueGaucheArriere = new VictorSP(ROUE_GAUCHE_ARRIERE); 
    this.roueDroiteArriere = new VictorSP(ROUE_DROITE_ARRIERE);
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/SpeedController.html
    this.roueGaucheAvant.setInverted(true);
    this.roueDroiteArriere.setInverted(true);

    //MecanumDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor)
    this.conduite = new MecanumDrive(roueGaucheAvant, roueGaucheArriere, roueDroiteAvant, roueDroiteArriere);
  }

  public void conduire(double vitesseY, double vitesseX)
  {
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html#driveCartesian-double-double-double-
    this.conduite.driveCartesian(vitesseY, vitesseX, 0);
  }

  public void tourner(double vitesseY, double vitesseX, double angle)
  {
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html#driveCartesian-double-double-double-
    this.conduite.driveCartesian(vitesseY, vitesseX, angle);
  }

  @Override
  public void initDefaultCommand(){}

}