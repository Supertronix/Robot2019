package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeRelacherEcoutille;

public class Manette implements RobotMap.Manette{

    protected Joystick manette;
    protected JoystickButton boutonRelacherEcoutille;

    public Manette(){
        this.manette = new Joystick(MANETTE);

        this.boutonRelacherEcoutille = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonRelacherEcoutille.whenPressed(new CommandeRelacherEcoutille());
    }
        
}