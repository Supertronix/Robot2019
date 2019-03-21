package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeDeplierCuisse;
import frc.robot.commande.CommandeDeplierJambe;
import frc.robot.commande.CommandeGrimperRobot;
import frc.robot.commande.CommandeInitialiserRobot;
import frc.robot.commande.configuration.CommandeTesterDeplierCuisse;
import frc.robot.commande.configuration.CommandeTesterDeplierJambe;

public class ManetteConfiguration extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonTestCuisse;
    protected JoystickButton boutonTestJambe;
    protected JoystickButton boutonTestMaintenirConsigne;
    protected JoystickButton boutonTesterInitialisationJambe;
    protected JoystickButton boutonTesterInitialisationCuisse;
    protected JoystickButton boutonTesterInitialiserRobot;
    protected JoystickButton boutonTesterMonterRobot;

    protected CommandeInitialiserRobot commandeTesterInitialiserRobot = null;
    
    protected ManetteConfiguration()
    // Design pattern Singleton fin
    {
    	System.out.println("new ManetteConfiguration()");
        this.manette = new Joystick(MANETTE);    	
        //this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_GAUCHE);
        //this.boutonTestCuisse.whenPressed(new CommandeTesterCuisse());
        //this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_DROIT);
        //this.boutonTestJambe.whenPressed(new CommandeTesterJambe());
        
        this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_B);
        this.boutonTestCuisse.whenPressed(new CommandeTesterDeplierCuisse(10,0.1f));
        this.boutonTestCuisse = new JoystickButton(this.manette, BOUTON_A);
        this.boutonTestCuisse.whenPressed(new CommandeTesterDeplierCuisse(-10,-0.1f));
        
        this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_Y);
        this.boutonTestJambe.whenPressed(new CommandeTesterDeplierJambe(10,0.1f));
        this.boutonTestJambe = new JoystickButton(this.manette, BOUTON_X);
        this.boutonTestJambe.whenPressed(new CommandeTesterDeplierJambe(-10,-0.1f));
        
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
;
        //this.commandeTesterEleverRobot = new CommandeTesterEleverRobot();
        //this.commandeTesterEleverRobot.start();
        
    }
    
    
    void monterVertical() {
    	
    }
            
}