package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.configuration.CommandeTesterCuisse;
import frc.robot.commande.configuration.CommandeTesterInitialiserCuisseAvecPID;
import frc.robot.commande.configuration.CommandeTesterInitialiserJambeAvecPID;
import frc.robot.commande.configuration.CommandeTesterInitialiserRobot;
import frc.robot.commande.configuration.CommandeTesterChangementConsigneCuisseAvecPID;
import frc.robot.commande.configuration.CommandeTesterChangementConsigneJambeAvecPID;
import frc.robot.commande.configuration.CommandeTesterJambe;
import frc.robot.commande.configuration.CommandeTesterMonterRobot;

public class ManetteConfiguration extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonTestCuisse;
    protected JoystickButton boutonTestJambe;
    protected JoystickButton boutonTestMaintenirConsigne;
    protected JoystickButton boutonTesterInitialisationJambe;
    protected JoystickButton boutonTesterInitialisationCuisse;
    protected JoystickButton boutonTesterInitialiserRobot;
    protected JoystickButton boutonTesterMonterRobot;





    

    // Design pattern Singleton pour récupérer la manette de n'importe quel code de roue
    protected static ManetteConfiguration instance = null;
    public static ManetteConfiguration getInstance()
    {
      if(null == ManetteConfiguration.instance) ManetteConfiguration.instance = new ManetteConfiguration();
      return ManetteConfiguration.instance;
    };

    protected CommandeTesterInitialiserRobot commandeTesterInitialiserRobot = null;


    
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
        this.boutonTestCuisse.whenPressed(new CommandeTesterChangementConsigneCuisseAvecPID(100));
        this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_A);
        this.boutonTestCuisse.whenPressed(new CommandeTesterChangementConsigneCuisseAvecPID(-100));
        
        this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_X);
        this.boutonTestJambe.whenPressed(new CommandeTesterChangementConsigneJambeAvecPID(200));
        this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_B);
        this.boutonTestJambe.whenPressed(new CommandeTesterChangementConsigneJambeAvecPID(-200));
        
        //this.boutonTesterInitialisationJambe = new JoystickButton(this.manette, BOUTON_DEMARRER);
        //this.boutonTesterInitialisationJambe.whenPressed(new CommandeTesterInitialiserJambeAvecPID());
        
        //this.boutonTesterInitialisationCuisse = new JoystickButton(this.manette, BOUTON_DEMARRER);
        //this.boutonTesterInitialisationCuisse.whenPressed(new CommandeTesterInitialiserCuisseAvecPID());
        //this.boutonTestMaintenirConsigne = new JoystickButton(this.manette, BOUTON_DEMARRER);
        
        this.boutonTesterInitialiserRobot = new JoystickButton(this.manette, BOUTON_DEMARRER);
        this.boutonTesterInitialiserRobot.whenPressed(new CommandeTesterInitialiserRobot());
        
        this.boutonTesterMonterRobot = new JoystickButton(this.manette, BOUTON_RETOUR);
        this.boutonTesterMonterRobot.whenPressed(new CommandeTesterMonterRobot());

        
        this.commandeTesterInitialiserRobot = new CommandeTesterInitialiserRobot();
        this.commandeTesterInitialiserRobot.start();
;
        //this.commandeTesterEleverRobot = new CommandeTesterEleverRobot();
        //this.commandeTesterEleverRobot.start();
        
    }
    
    
    void monterVertical() {
    	
    }
    
    public static void desactiverInstance()
    {
    	ManetteConfiguration.instance = null;    	
    }
        
}