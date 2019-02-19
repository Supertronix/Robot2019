package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.configuration.CommandeTesterCuisse;
import frc.robot.commande.configuration.CommandeTesterEleverRobot;
import frc.robot.commande.configuration.CommandeTesterChangementConsigneCuisseAvecPID;
import frc.robot.commande.configuration.CommandeTesterJambe;

public class ManetteConfiguration extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonTestCuisse;
    protected JoystickButton boutonTestJambe;

    // Design pattern Singleton pour récupérer la manette de n'importe quel code de roue
    protected static ManetteConfiguration instance = null;
    public static ManetteConfiguration getInstance()
    {
      if(null == ManetteConfiguration.instance) ManetteConfiguration.instance = new ManetteConfiguration();
      return ManetteConfiguration.instance;
    };

    protected CommandeTesterEleverRobot commandeTesterEleverRobot = null;
    
    protected ManetteConfiguration()
    // Design pattern Singleton fin
    {
    	System.out.println("new ManetteConfiguration()");
        this.manette = new Joystick(MANETTE);    	
        //this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_GAUCHE);
        //this.boutonTestCuisse.whenPressed(new CommandeTesterCuisse());
        //this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_DROIT);
        //this.boutonTestJambe.whenPressed(new CommandeTesterJambe());
        this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_Y);
        this.boutonTestCuisse.whenPressed(new CommandeTesterChangementConsigneCuisseAvecPID(5));
        
        //this.commandeTesterEleverRobot = new CommandeTesterEleverRobot();
        //this.commandeTesterEleverRobot.start();
    }
    
    public static void desactiverInstance()
    {
    	ManetteConfiguration.instance = null;    	
    }
        
}