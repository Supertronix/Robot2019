package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.configuration.CommandeTesterDeplierCuisse;
import frc.robot.commande.configuration.CommandeTesterDeplierCuisseAvecMinirupteur;
import frc.robot.commande.configuration.CommandeTesterDeplierJambe;
import frc.robot.commande.configuration.CommandeTesterDeplierJambeAvecMinirupteur;

public class ManetteConfiguration extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonTestCuisseDeplier;
    protected JoystickButton boutonTestCuissePlier;
    protected JoystickButton boutonTestJambeDeplier;
    protected JoystickButton boutonTestJambePlier;
    protected JoystickButton boutonTestMaintenirConsigne;
    protected JoystickButton boutonTesterInitialisationJambe;
    protected JoystickButton boutonTesterInitialisationCuisse;
    protected JoystickButton boutonTesterInitialiserRobot;
    protected JoystickButton boutonTesterMonterRobot;

    //protected CommandeInitialiserRobot commandeTesterInitialiserRobot = null;
    
    protected ManetteConfiguration()
    {
    	System.out.println("new ManetteConfiguration()");
        this.manette = new Joystick(MANETTE);    	
        
        this.boutonTestCuisseDeplier = new JoystickButton(this.manette, BOUTON_B);
        this.boutonTestCuisseDeplier.whenPressed(new CommandeTesterDeplierCuisseAvecMinirupteur(20,0.1f));
        this.boutonTestCuissePlier = new JoystickButton(this.manette, BOUTON_A);
        this.boutonTestCuissePlier.whenPressed(new CommandeTesterDeplierCuisseAvecMinirupteur(-20,-0.1f));
        this.boutonTestJambeDeplier = new JoystickButton(this.manette, BOUTON_Y);
        this.boutonTestJambeDeplier.whenPressed(new CommandeTesterDeplierJambeAvecMinirupteur(20,0.3f));
        this.boutonTestJambePlier = new JoystickButton(this.manette, BOUTON_X);
        this.boutonTestJambePlier.whenPressed(new CommandeTesterDeplierJambeAvecMinirupteur(-20,-0.3f));
        
        //this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_GAUCHE);
        //this.boutonTestCuisse.whenPressed(new CommandeTesterCuisse());
        //this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_DROIT);
        //this.boutonTestJambe.whenPressed(new CommandeTesterJambe());
                
        //this.boutonTesterInitialisationJambe = new JoystickButton(this.manette, BOUTON_DEMARRER);
        //this.boutonTesterInitialisationJambe.whenPressed(new CommandeTesterInitialiserJambeAvecPID());
        
        //this.boutonTesterInitialisationCuisse = new JoystickButton(this.manette, BOUTON_DEMARRER);
        //this.boutonTesterInitialisationCuisse.whenPressed(new CommandeTesterInitialiserCuisseAvecPID());
        //this.boutonTestMaintenirConsigne = new JoystickButton(this.manette, BOUTON_DEMARRER);
        
        //this.boutonTesterInitialiserRobot = new JoystickButton(this.manette, BOUTON_DEMARRER);
        //this.boutonTesterInitialiserRobot.whenPressed(new CommandeInitialiserRobot());
        
        //this.boutonTesterMonterRobot = new JoystickButton(this.manette, BOUTON_RETOUR);
        //this.boutonTesterMonterRobot.whenPressed(new CommandeGrimperRobot());

        
        //this.commandeTesterInitialiserRobot = new CommandeInitialiserRobot();
        //this.commandeTesterInitialiserRobot.start();
        //this.commandeTesterEleverRobot = new CommandeTesterEleverRobot();
        //this.commandeTesterEleverRobot.start();
    }
}