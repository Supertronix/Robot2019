package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeAllumerTableTournante;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeEteindreTableTournante;
import frc.robot.commande.CommandeRelacherEcoutille;

public class ManetteConfigurationRobotPratique extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonControllerAttrapeur;
    protected JoystickButton boutonAllumerTableTournante;
    protected JoystickButton boutonEteindreTableTournante;

    protected ManetteConfigurationRobotPratique()
    {
        this.manette = new Joystick(MANETTE);
        
        this.boutonControllerAttrapeur = new JoystickButton(this.manette, this.BOUTON_X);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
        
        this.boutonAllumerTableTournante = new JoystickButton(this.manette, this.BOUTON_DROIT);
        this.boutonAllumerTableTournante.whenPressed(new CommandeAllumerTableTournante());
        this.boutonEteindreTableTournante = new JoystickButton(this.manette, this.BOUTON_GAUCHE);
        this.boutonEteindreTableTournante.whenPressed(new CommandeEteindreTableTournante());
    }
        
}