package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeRelacherEcoutille;

public class ManetteCompetition extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonControllerAttrapeur;

    protected ManetteCompetition()
    // Design pattern Singleton fin
    {
        this.manette = new Joystick(MANETTE);
        this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
    }
        
}