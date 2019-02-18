package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.configuration.CommandeTesterCuisse;

public class ManetteConfiguration extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonTestCuisse;

    // Design pattern Singleton pour récupérer la manette de n'importe quel code de roue
    protected static ManetteConfiguration instance = null;
    public static ManetteConfiguration getInstance()
    {
      if(null == ManetteConfiguration.instance) ManetteConfiguration.instance = new ManetteConfiguration();
      return ManetteConfiguration.instance;
    };

    protected ManetteConfiguration()
    // Design pattern Singleton fin
    {
    	System.out.println("new ManetteConfiguration()");
        this.manette = new Joystick(MANETTE);    	
        this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_DROIT);
        this.boutonTestCuisse.whenPressed(new CommandeTesterCuisse());
    }
        
}