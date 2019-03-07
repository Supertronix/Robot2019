package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.attrapeur.CommandeAllumerTableTournante;
import frc.robot.commande.attrapeur.CommandeArmerAttrapeur;
import frc.robot.commande.attrapeur.CommandeDesactiverAnnonceAttrapage;
import frc.robot.commande.attrapeur.CommandeDescendreGoupille;
import frc.robot.commande.attrapeur.CommandeEteindreTableTournante;
import frc.robot.commande.attrapeur.CommandeMonterGoupille;
import frc.robot.commande.attrapeur.CommandeRelacherEcoutille;

public class ManetteConfigurationRobotPratique extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonControllerAttrapeur;
    protected JoystickButton boutonAllumerTableTournante;
    protected JoystickButton boutonEteindreTableTournante;
    protected JoystickButton boutonMonterGoupille;
    protected JoystickButton boutonDescendreGoupille;
    protected JoystickButton boutonAnnulerAnnonceAttrapage;

    protected ManetteConfigurationRobotPratique()
    {
        this.manette = new Joystick(MANETTE);
        
        this.boutonControllerAttrapeur = new JoystickButton(this.manette, this.BOUTON_Y);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
        
        this.boutonAllumerTableTournante = new JoystickButton(this.manette, this.BOUTON_DROIT);
        this.boutonAllumerTableTournante.whenPressed(new CommandeAllumerTableTournante());
        this.boutonEteindreTableTournante = new JoystickButton(this.manette, this.BOUTON_GAUCHE);
        this.boutonEteindreTableTournante.whenPressed(new CommandeEteindreTableTournante());

        this.boutonMonterGoupille = new JoystickButton(this.manette, this.BOUTON_B);
        this.boutonMonterGoupille.whenPressed(new CommandeMonterGoupille());
        this.boutonDescendreGoupille = new JoystickButton(this.manette, this.BOUTON_X);
        this.boutonDescendreGoupille.whenPressed(new CommandeDescendreGoupille());
        
        this.boutonAnnulerAnnonceAttrapage = new JoystickButton(this.manette, this.BOUTON_A);
        this.boutonAnnulerAnnonceAttrapage.whenPressed(new CommandeDesactiverAnnonceAttrapage());
    }
        
}