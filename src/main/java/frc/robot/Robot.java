package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.interaction.CapteurUltrason;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Roues;
import frc.robot.sousysteme.RouesMecanumFormuleLogique;
import frc.robot.sousysteme.RouesMecanumFormuleMoyenne;

public class Robot extends TimedRobot {

  public static Attrapeur attrapeur;
  public static Roues roues;

  protected Manette manette;
  protected Camera camera;
  protected CapteurUltrason capteurUltrason;
  
  @Override
  public void robotInit() 
  {
    this.attrapeur = new Attrapeur();
    this.roues = new RouesMecanumFormuleLogique();
    
    this.capteurUltrason = new CapteurUltrason();
    this.camera = new Camera();
    this.manette = Manette.getInstance();//doit etre en dernier
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

  @Override
  public void teleopPeriodic() 
  {
	this.roues.conduire();
    this.capteurUltrason.getDistance();
  }

  @Override
  public void testPeriodic() {
  }
}
