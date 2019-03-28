package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.interaction.AnimateurDisque;
import frc.robot.interaction.AnimateurLed;
import frc.robot.interaction.Camera;
import frc.robot.interaction.Manette;
import frc.robot.interaction.CapteurUltrason;
import frc.robot.interaction.DetecteurEcoutilleAttrapee;
import frc.robot.sousysteme.Attrapeur;
import frc.robot.sousysteme.Cuisse;
import frc.robot.sousysteme.Jambe;
import frc.robot.sousysteme.Roues;
import frc.robot.sousysteme.RouesMecanum;
import frc.robot.sousysteme.Treuil;

public class Robot extends TimedRobot {

  // Sous-systemes
  public static Roues roues;
  public static Attrapeur attrapeur;
  public static Cuisse cuisse;
  public static Jambe jambe;
  public static Treuil treuil;

  // Interactions
  protected Manette manette;
  protected Camera camera;
  protected CapteurUltrason capteurUltrason;
  protected DetecteurEcoutilleAttrapee detecteurEcoutille;
  public static AnimateurLed animateurLed;
  public static AnimateurDisque animateurDisque;
  
  @Override
  public void robotInit() 
  {
	Journal.activer(); Journal.activerNiveau(1);
	Robot.roues = new RouesMecanum();
	Robot.attrapeur = new Attrapeur();
	Robot.treuil = new Treuil();

    Robot.cuisse = new Cuisse();
    Robot.jambe = new Jambe();
    
    //this.capteurUltrason = new CapteurUltrason();
    this.camera = new Camera();
	this.detecteurEcoutille = new DetecteurEcoutilleAttrapee();
	this.animateurLed = new AnimateurLed(this.detecteurEcoutille);
	this.animateurDisque = new AnimateurDisque(this.detecteurEcoutille);
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
	  //Journal.ecrire("teleopPeriodic()");
	  Scheduler.getInstance().run();
	  Manette.getInstance().executerActions(); 
  }

  @Override
  public void testPeriodic() {
  }
}
