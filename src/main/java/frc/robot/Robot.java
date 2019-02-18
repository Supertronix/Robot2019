package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commande.configuration.CommandeTesterCuisse;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteCompetition;
import frc.robot.interaction.ManetteConfiguration;
import frc.robot.interaction.CapteurUltrason;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Cuisse;
import frc.robot.sousysteme.Roues;
import frc.robot.sousysteme.RouesMecanumFormuleLogique;

public class Robot extends TimedRobot {

  public static Roues roues;
  public static Attrapeur attrapeur;
  public static Cuisse cuisse;

  protected Manette manette;
  protected Camera camera;
  protected CapteurUltrason capteurUltrason;
  
  @Override
  public void robotInit() 
  {
	//Robot.roues = new RouesMecanumFormuleLogique();
	//Robot.attrapeur = new Attrapeur();
    this.cuisse = new Cuisse();
    
    //this.capteurUltrason = new CapteurUltrason();
    //this.camera = new Camera();
    //this.manette = ManetteCompetition.getInstance();//doit etre en dernier
    this.manette = ManetteConfiguration.getInstance();//doit etre en dernier
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
	 Scheduler.getInstance().run();

     //System.out.println("teleopPeriodic()");
	//Robot.roues.conduire();
	//this.capteurUltrason.detecter();
    //Robot.cuisse.monter();
    //System.out.println("Test Cuisse Moteur " + RobotMap.Cuisse.MOTEUR_SECONDAIRE);
  }

  @Override
  public void testPeriodic() {
  }
}
