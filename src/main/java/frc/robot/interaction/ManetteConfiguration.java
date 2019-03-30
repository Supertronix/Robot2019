package frc.robot.interaction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Journal;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commande.CommandeDeplierCuisse;
import frc.robot.commande.CommandeDeroulerTreuil;
import frc.robot.commande.CommandeInitialiserRobot;
import frc.robot.commande.CommandeRoulerTreuil;

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
    
    protected JoystickButton boutonTestGrimpage;
    protected JoystickButton boutonRoulerTreuil;
    protected JoystickButton boutonDeroulerTreuil;

    protected CommandeInitialiserRobot commandeTesterInitialiserRobot = null;
    
    protected ManetteConfiguration()
    {
    	System.out.println("new ManetteConfiguration()");
        this.manette = new Joystick(MANETTE);    	
        
        /*
        this.boutonTestCuisseDeplier = new JoystickButton(this.manette, BOUTON_B);
        this.boutonTestCuisseDeplier.whenPressed(new CommandeTesterDeplierCuisseAvecMinirupteur(100,0.1f));
        this.boutonTestCuissePlier = new JoystickButton(this.manette, BOUTON_A);
        this.boutonTestCuissePlier.whenPressed(new CommandeTesterDeplierCuisseAvecMinirupteur(-100,-0.1f));
        this.boutonTestJambeDeplier = new JoystickButton(this.manette, BOUTON_Y);
        this.boutonTestJambeDeplier.whenPressed(new CommandeTesterDeplierJambeAvecMinirupteur(100,0.3f));
        this.boutonTestJambePlier = new JoystickButton(this.manette, BOUTON_X);
        this.boutonTestJambePlier.whenPressed(new CommandeTesterDeplierJambeAvecMinirupteur(-100,-0.3f));
         */
        //this.boutonTestGrimpage = new JoystickButton(this.manette, this.BOUTON_DEMARRER);
        //this.boutonTestGrimpage.whenPressed(new CommandeGrimperRobot());
        

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

        
        this.commandeTesterInitialiserRobot = new CommandeInitialiserRobot();
        this.commandeTesterInitialiserRobot.start();
        //this.commandeTesterEleverRobot = new CommandeTesterEleverRobot();
        //this.commandeTesterEleverRobot.start();
        
        //this.boutonAllumerTableTournante = new JoystickButton(this.manette, this.BOUTON_DROIT);
        //this.boutonAllumerTableTournante.whenPressed(new CommandeAllumerTableTournante());
        //this.boutonEteindreTableTournante = new JoystickButton(this.manette, this.BOUTON_GAUCHE);
        //this.boutonEteindreTableTournante.whenPressed(new CommandeEteindreTableTournante());
        
        //this.boutonRoulerTreuil = new JoystickButton(this.manette, BOUTON_DROIT);
        //this.boutonRoulerTreuil.whenPressed(new CommandeRoulerTreuil());
        //this.boutonDeroulerTreuil = new JoystickButton(this.manette, BOUTON_GAUCHE);
        //this.boutonDeroulerTreuil.whenPressed(new CommandeDeroulerTreuil());
    }
    
    public void executerActions()
    {
    	//this.deplacerTreuilSelonAxes();
    	//this.roulerEtDeroulerTreuil();
    	this.plierEtDeplierCuisse();
    }

    public void plierEtDeplierCuisse()
    {
    	Journal.ecrire("Axe " + this.getAxeMainGauche().y);
    	if(this.getAxeMainGauche().y > 0.1) 
    	{
    		Robot.cuisse.monter((float) this.getAxeMainGauche().y);
    	}
    	else
    	{
    		Robot.cuisse.arreter();
    	}
    }
    
    protected Command commandeRoulerTreuil = null;
    protected Command commandeDeroulerTreuil = null;
    public void roulerEtDeroulerTreuil()
    {
    	Journal.ecrire("Declencheur main droite " + this.getDeclencheurMainDroite());
    	if(this.getDeclencheurMainDroite() > 0.5 && this.commandeDeroulerTreuil == null) 
    	{
    		this.commandeRoulerTreuil = null;
    		this.commandeDeroulerTreuil = new CommandeDeroulerTreuil();
    		this.commandeDeroulerTreuil.start();
    	}
    	Journal.ecrire("Declencheur main gauche " + this.getDeclencheurMainGauche());
    	if(this.getDeclencheurMainGauche() > 0.5 && this.commandeRoulerTreuil == null) 
    	{
    		this.commandeDeroulerTreuil = null;
    		this.commandeRoulerTreuil = new CommandeRoulerTreuil();
    		this.commandeRoulerTreuil.start();
    	}
    }
    public void deplacerTreuilSelonAxes()
    {
    	  Journal.ecrire("treuil.getPositionLecteur = " + Robot.treuil.getPosition());
      	  Robot.treuil.tourner((float)(this.getAxeMainGauche().y));
      	  Journal.ecrire("treuil.estRentre : " + Robot.treuil.estRentre());
      	  Journal.ecrire("treuil.estSorti : " + Robot.treuil.estSorti());
    }
    
    public void deplacerCuisseSelonAxes()
    {
    	
    }

}

