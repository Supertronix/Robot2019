package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeRelacherEcoutille;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import java.lang.Math;

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
      public Axe(double x, double y) { this.x = (Math.abs(x)>SEUIL)?-x:0; this.y = (Math.abs(y)>SEUIL)?-y:0;}
      public double x;
      public double y;
    }

    protected Axe axeMainDroite = null;
    protected Axe axeMainGauche = null;

    public Axe getAxeMainDroite()
    {
      //this.axeMainDroite = new Axe(manette.getX(Hand.kRight),manette.getY(Hand.kRight)); // bug dans lib wpi
      this.axeMainDroite = new Axe(manette.getRawAxis(MAIN_DROITE_AXE_X), manette.getRawAxis(MAIN_DROITE_AXE_Y));
      return this.axeMainDroite;
    }
    public Axe getAxeMainGauche()
    {
      //this.axeMainGauche = new Axe(manette.getX(Hand.kLeft),manette.getY(Hand.kLeft));
      this.axeMainGauche = new Axe(manette.getRawAxis(MAIN_GAUCHE_AXE_X), manette.getRawAxis(MAIN_GAUCHE_AXE_Y));
		  return this.axeMainGauche;
    }

    // 1 = droite, 0 tout droit, -1 = gauche
    public int getDirection()
    {
      if(this.axeMainDroite.y > SEUIL) if(this.axeMainGauche.y < -SEUIL) return -1;
      if(this.axeMainGauche.y > SEUIL) if(this.axeMainDroite.y < -SEUIL) return 1;
      return 0;
    }
        
}