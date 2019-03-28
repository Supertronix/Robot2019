package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeDeroulerTreuil;
import frc.robot.commande.CommandeInitialiserRobot;
import frc.robot.commande.CommandeRoulerTreuil;
import frc.robot.commande.attrapeur.CommandeArmerAttrapeur;
import frc.robot.commande.attrapeur.CommandeDescendreGoupille;
import frc.robot.commande.attrapeur.CommandeMonterGoupille;
import frc.robot.commande.attrapeur.CommandeRelacherEcoutille;

public class ManettePratique extends Manette implements RobotMap.Manette{

    protected JoystickButton boutonControllerAttrapeur;
    protected JoystickButton boutonAllumerTableTournante;
    protected JoystickButton boutonEteindreTableTournante;
    protected JoystickButton boutonMonterGoupille;
    protected JoystickButton boutonDescendreGoupille;
    protected JoystickButton boutonAnnulerAnnonceAttrapage;
    protected JoystickButton boutonRoulerTreuil;
    protected JoystickButton boutonDeroulerTreuil;
    protected CommandeInitialiserRobot commandeTesterInitialiserRobot = null;

    protected ManettePratique()
    {
        this.manette = new Joystick(MANETTE);
        
        this.boutonMonterGoupille = new JoystickButton(this.manette, this.BOUTON_B);
        this.boutonMonterGoupille.whenPressed(new CommandeMonterGoupille());
        this.boutonDescendreGoupille = new JoystickButton(this.manette, this.BOUTON_A);
        this.boutonDescendreGoupille.whenPressed(new CommandeDescendreGoupille());
        
        this.boutonControllerAttrapeur = new JoystickButton(this.manette, this.BOUTON_DROIT);
        this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());        
        //this.boutonAnnulerAnnonceAttrapage = new JoystickButton(this.manette, this.BOUTON_A);
        //this.boutonAnnulerAnnonceAttrapage.whenPressed(new CommandeDesactiverAnnonceAttrapage());
        
        this.boutonRoulerTreuil = new JoystickButton(this.manette, this.BOUTON_Y);
        this.boutonRoulerTreuil.whenPressed(new CommandeRoulerTreuil());
        this.boutonDeroulerTreuil = new JoystickButton(this.manette, this.BOUTON_X);
        this.boutonDeroulerTreuil.whenPressed(new CommandeDeroulerTreuil());
        
        this.commandeTesterInitialiserRobot = new CommandeInitialiserRobot();
        this.commandeTesterInitialiserRobot.start();   
    }
    
    public void executerActions()
    {
  	  //Journal.ecrire("Test Cuisse Moteur " + RobotMap.Cuisse.MOTEUR_SECONDAIRE);	
    	Robot.cuisse.synchroniser();
    	Robot.animateurDisque.animerSelonSignal();
    	Robot.animateurLed.animerSelonSignal();
    	Robot.roues.conduire();
    	//this.capteurUltrason.detecter();

    }
    
        
}