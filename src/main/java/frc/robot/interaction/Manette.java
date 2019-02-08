package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeArmerAttrapeur;
import frc.robot.commande.CommandeRelacherEcoutille;

public class Manette implements RobotMap.Manette{

    protected Joystick manette;
    protected JoystickButton boutonControllerAttrapeur;

    public Manette(){
        this.manette = new Joystick(MANETTE);

        this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
    }

    public double getY1(){
		return manette.getRawAxis(CONDUITE_Y_GAUCHE);
    }
    
	public double getY2(){
		return manette.getRawAxis(CONDUITE_Y_DROITE);
	}
        
}