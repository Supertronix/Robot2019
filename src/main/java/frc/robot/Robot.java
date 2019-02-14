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
  protected double angle;
  @Override
  public void teleopPeriodic() 
  {
    System.out.println("y gauche=" + manette.getAxeMainGauche().y + " y droit=" + manette.getAxeMainDroite().y);
    this.yMoyen = (manette.getAxeMainDroite().y + manette.getAxeMainGauche().y)/2;
    this.xMoyen = (manette.getAxeMainDroite().x + manette.getAxeMainGauche().x)/2;
    //System.out.println("xMoyen = " + this.xMoyen + " yMoyen = " + this.yMoyen);
    Scheduler.getInstance().run();
    //roues.conduire(-manette.getAxeMainGauche().y, -manette.getAxeMainDroite().y; // avec TankDrive

    System.out.println("Direction " + manette.getDirection());
    if(manette.getDirection() == 0) // si la direction est avant ou arrière
    {
      roues.conduire(this.yMoyen, this.xMoyen);
    }
    else // si la direction est droite (+1) ou gauche (-1)
    {
      this.angle = manette.getDirection()*(Math.abs(manette.getAxeMainDroite().y) + Math.abs(manette.getAxeMainGauche().y));
      roues.tourner(manette.getAxeMainGauche().y, manette.getAxeMainDroite().y);
    }
  }

  @Override
  public void testPeriodic() {
  }
}
