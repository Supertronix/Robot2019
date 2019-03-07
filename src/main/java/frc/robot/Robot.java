package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.interaction.ManetteCompetition;
import frc.robot.interaction.ManetteConfiguration;
import frc.robot.interaction.CapteurUltrason;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Cuisse;
import frc.robot.sousysteme.Jambe;
import frc.robot.sousysteme.Roues;
import frc.robot.sousysteme.RouesMecanum;

public class Robot extends TimedRobot {

  public static Roues roues;
  public static Attrapeur attrapeur;
  public static Cuisse cuisse;
  public static Jambe jambe;

  protected Manette manette;
  protected Camera camera;
  protected CapteurUltrason capteurUltrason;
  
  @Override
  public void robotInit() 
  {
	Robot.roues = new RouesMecanum();
	Robot.attrapeur = new Attrapeur();

    //Robot.cuisse = new Cuisse();
    //Robot.jambe = new Jambe();
    
    //this.capteurUltrason = new CapteurUltrason();
    //this.camera = new Camera();
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

  //protected CommandeTesterEleverRobot commandeTesterEleverRobot = null;
  @Override
  public void teleopInit() 
  {
	 Manette.desactiverInstance();
	 this.manette = Manette.getInstance();
  }

  @Override
  public void teleopPeriodic() 
  {
	Scheduler.getInstance().run();
	 
	//Robot.cuisse.lirePosition();
	//Robot.jambe.lirePosition();
	 
    //System.out.println("teleopPeriodic()");
	//Robot.roues.conduire();
	//this.capteurUltrason.detecter();
    //System.out.println("Test Cuisse Moteur " + RobotMap.Cuisse.MOTEUR_SECONDAIRE);
  }

  @Override
  public void testPeriodic() {
  }
}
