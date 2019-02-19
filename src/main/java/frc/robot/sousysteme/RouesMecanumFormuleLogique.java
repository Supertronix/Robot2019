package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteCompetition;
import frc.robot.interaction.ManetteConfiguration;

public class RouesMecanumFormuleLogique extends Roues{

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

  public RouesMecanumFormuleLogique()
  {
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/VictorSP.html
    this.roueGaucheAvant = new VictorSP(ROUE_GAUCHE_AVANT);
    this.roueDroiteAvant = new VictorSP(ROUE_DROITE_AVANT);
    this.roueGaucheArriere = new VictorSP(ROUE_GAUCHE_ARRIERE); 
    this.roueDroiteArriere = new VictorSP(ROUE_DROITE_ARRIERE);
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/SpeedController.html
    this.roueGaucheAvant.setInverted(true);
    this.roueGaucheArriere.setInverted(true);

    //MecanumDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor)
    //this.conduite = new MecanumDrive(roueGaucheAvant, roueGaucheArriere, roueDroiteAvant, roueDroiteArriere);
  }

  public void conduire(double vitesseY, double vitesseX)
  {
    System.out.println("conduire("+vitesseY+","+vitesseX+")");
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html#driveCartesian-double-double-double-
    this.conduite.driveCartesian(vitesseY, vitesseX, 0);
  }
  
  //protected double yMoyen;
  protected double xMoyen;
  //protected double angle;
  
  public void conduire()
  {
	  Manette manette = ManetteConfiguration.getInstance();
	    System.out.println("y gauche=" + manette.getAxeMainGauche().y + " y droit=" + manette.getAxeMainDroite().y);
	    //this.yMoyen = (manette.getAxeMainDroite().y + manette.getAxeMainGauche().y)/2;
	    //this.xMoyen = (manette.getAxeMainDroite().x + manette.getAxeMainGauche().x)/2;
	    //System.out.println("xMoyen = " + this.xMoyen + " yMoyen = " + this.yMoyen);
	    //roues.conduire(-manette.getAxeMainGauche().y, -manette.getAxeMainDroite().y; // avec TankDrive
	    //Formule 2017 (x + yGauche, yDroite - x, yGauche - x, x + yDroite);
	    this.conduireToutesDirections(
	      (manette.getAxeMainGauche().y + manette.getAxeMainGauche().x + manette.getAxeMainDroite().x), 
	      (manette.getAxeMainGauche().y - manette.getAxeMainGauche().x - manette.getAxeMainDroite().x),	      
	      (manette.getAxeMainGauche().y - manette.getAxeMainGauche().x + manette.getAxeMainDroite().x), 
	      (manette.getAxeMainGauche().y + manette.getAxeMainGauche().x - manette.getAxeMainDroite().x) 
	      );

  }

  public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) 
  {
    this.roueGaucheAvant.set(limiter(vitesseAvantGauche));
    this.roueGaucheArriere.set(limiter(vitesseArriereGauche));
    this.roueDroiteAvant.set(limiter(vitesseAvantDroite));
    this.roueDroiteArriere.set(limiter(vitesseArriereDroite));
  }

  public void tourner(double vitesseGauche, double vitesseDroite)
  {
    System.out.println("tourner("+vitesseGauche+","+vitesseDroite+")");
    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html#driveCartesian-double-double-double-
    // this.conduite.driveCartesian(0,0, angle*45);
    this.roueGaucheAvant.set(vitesseGauche);
    this.roueGaucheArriere.set(-vitesseGauche);
    this.roueDroiteAvant.set(vitesseDroite);
    this.roueDroiteArriere.set(-vitesseDroite);
  }

  public void arreter()
  {
    this.roueGaucheAvant.set(0);
    this.roueGaucheArriere.set(0);
    this.roueDroiteAvant.set(0);
    this.roueDroiteArriere.set(0);
  }

  @Override
  public void initDefaultCommand(){}

}