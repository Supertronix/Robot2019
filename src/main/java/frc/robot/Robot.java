package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Roues;

public class Robot extends TimedRobot {

  public static Attrapeur attrapeur;
  public static Manette manette;
  public static Roues roues;
  public static Camera camera;
  

  @Override
  public void robotInit() {
    attrapeur = new Attrapeur();
    roues = new Roues();
    camera = new Camera();
    manette = new Manette();//doit etre en dernier
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    roues.conduire(-manette.getY1(), -manette.getY2());
  }

  @Override
  public void testPeriodic() {
  }
}
