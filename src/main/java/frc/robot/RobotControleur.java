package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.Manette;
import frc.robot.sousysteme.Attrapeur;

public class RobotControleur extends TimedRobot {

  public static Attrapeur attrapeur = null;
  public static Manette manette = null;

  @Override
  public void robotInit() {
    attrapeur = new Attrapeur();
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
  }

  @Override
  public void testPeriodic() {
  }
}
