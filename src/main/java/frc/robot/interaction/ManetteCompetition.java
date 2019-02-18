package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeRelacherEcoutille;

public class ManetteCompetition extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonControllerAttrapeur;

    // Design pattern Singleton pour récupérer la manette de n'importe quel code de roue
    protected static ManetteCompetition instance = null;
    public static ManetteCompetition getInstance()
    {
      if(null == ManetteCompetition.instance) ManetteCompetition.instance = new ManetteCompetition();
      return ManetteCompetition.instance;
    };

    protected ManetteCompetition()
    // Design pattern Singleton fin
    {
        this.manette = new Joystick(MANETTE);
        this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
    }
        
}