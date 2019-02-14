package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Roues;
import frc.robot.sousysteme.RouesMecanum;

public class Robot extends TimedRobot {

  public static Attrapeur attrapeur;
  public static Manette manette;
  public static Roues roues;
  //public static Camera camera;
  
  @Override
  public void robotInit() 
  {
    attrapeur = new Attrapeur();
    roues = new RouesMecanum();
    //camera = new Camera();
    manette = new Manette();//doit etre en dernier
  }

  @Override
  public void robotPeriodic() 
  {
  }

  @Override
  public void disabledInit() 
  {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() 
  {
  }

  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
  }

  protected double yMoyen;
  protected double xMoyen;
  @Override
  public void teleopPeriodic() 
  {
    //System.out.println("y gauche=" + manette.getAxeMainGauche().y + " y droit=" + manette.getAxeMainDroite().y);
    this.yMoyen = (manette.getAxeMainDroite().y + manette.getAxeMainGauche().y)/2;
    this.xMoyen = (manette.getAxeMainDroite().x + manette.getAxeMainGauche().x)/2;
    //System.out.println("xMoyen = " + this.xMoyen + " yMoyen = " + this.yMoyen);
    Scheduler.getInstance().run();
    //roues.conduire(-manette.getAxeMainGauche().y, -manette.getAxeMainDroite().y; // avec TankDrive

     System.out.println("Direction " + manette.getDirection());
    switch(manette.getDirection())
    {
      case 1: // DROITE
      {
        double angle = Math.abs(manette.getAxeMainDroite().x + manette.getAxeMainGauche().x);
        roues.tourner(this.yMoyen, this.xMoyen, angle);
      }
      break;
      case -1: // GAUCHE
      { 
        double angle = Math.abs(manette.getAxeMainDroite().x + manette.getAxeMainGauche().x);
        roues.tourner(this.yMoyen, this.xMoyen, angle);
      }
      break;
      case 0: // AVANT - ARRIERE 
        roues.conduire(this.yMoyen, this.xMoyen);
      break;
    }
  }

  @Override
  public void testPeriodic() {
  }
}
