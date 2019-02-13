package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeRelacherEcoutille;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Manette implements RobotMap.Manette{

    protected Joystick manette;
    protected JoystickButton boutonControllerAttrapeur;

    public Manette()
    {
        this.manette = new Joystick(MANETTE);

        this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
    }

    public class Axe 
    {
      public Axe(double x, double y) { this.x = x; this.y = y;}
      public double x;
      public double y;
    }

    public Axe getAxeMainDroite()
    {
		  return new Axe(manette.getX(Hand.kRight),manette.getY(Hand.kRight));
    }
    public Axe getAxeMainGauche()
    {
		  return new Axe(manette.getX(Hand.kLeft),manette.getY(Hand.kLeft));
    }
    
  //public double getY2()
  //{
	//	  return manette.getRawAxis(CONDUITE_Y_DROITE);
	//}
        
}