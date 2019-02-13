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

  protected double y;
  protected double x;
  @Override
  public void teleopPeriodic() 
  {
    this.y = (manette.getAxeMainDroite().y + manette.getAxeMainGauche().y)/2;
    this.x = (manette.getAxeMainDroite().x + manette.getAxeMainGauche().x)/2;
    Scheduler.getInstance().run();
    //roues.conduire(-manette.getAxeMainGauche().y, -manette.getAxeMainDroite().y; // avec TankDrive
    switch(manette.getDirection())
    {
      case 1: // DROITE
      {
        double angle = Math.abs(manette.getAxeMainDroite().x + manette.getAxeMainGauche().x);
        roues.tourner(this.y, this.x, angle);
      }
      break;
      case -1: // GAUCHE
      { 
        double angle = Math.abs(manette.getAxeMainDroite().x + manette.getAxeMainGauche().x);
        roues.tourner(this.y, this.x, angle);
      }
      break;
      case 0: // AVANT - ARRIERE 
        roues.conduire(this.y, this.x);
      break;
    }
  }

  @Override
  public void testPeriodic() {
  }
}
