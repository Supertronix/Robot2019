package frc.robot;

public interface RobotMap {
  
  // Aussi appelé Hanche par l'équipe	
  public interface Cuisse
  {
	  public static final int MOTEUR_PRINCIPAL = 4; // C1 - MASTER - VictorSRX - BREAK MODE - ENCODEUR
	  public static final int MOTEUR_SECONDAIRE = 3; // C2 - ESCLAVE - VictorSPX - BREAK MODE

	  public static final int ENCODEUR_MOTEUR_PRINCIPAL_A = 10; 
	  public static final int ENCODEUR_MOTEUR_PRINCIPAL_B = 11; 
  }
  
 // Aussi appelé Genoux par l'équipe  
  public interface Jambe
  {
	  public static final int MOTEUR_PRINCIPAL = 2; // G1 - MASTER - TalonSRX - BREAK MODE - ENCODEUR
	  public static final int MOTEUR_SECONDAIRE = 5; // G2 - ESCLAVE - TalonSRX - BREAK MODE
	  
	  public static final int ENCODEUR_MOTEUR_PRINCIPAL_A = 10; 
	  public static final int ENCODEUR_MOTEUR_PRINCIPAL_B = 11; 
  }
	
  public interface Attrapeur
  {
    public static final int MOTEUR_ATTRAPEUR = 6;
    public static final float MOTEUR_ATTRAPEUR_VITESSE_OUVERTURE = -0.5f;
    public static final float MOTEUR_ATTRAPEUR_VITESSE_FERMETURE = 0.5f;

    public static final int MOTEUR_TABLE_TOURNANTE = 0; // TODO : trouver la vitesse
    public static final float MOTEUR_TABLE_TOURNANTE_VITESSE = -0.5f;
    public static final float MOTEUR_TABLE_TOURNANTE_ARRET = 0.0f;
   
    public static final int SERVO_CREMAILLERE = 0;  // TODO : trouver les angles maintenue et relachée
    public static final float SERVO_CREMAILLERE_ANGLE_MAINTENU = 0.0f; //the angle the release servo is for the duration of match
    public static final float SERVO_CREMAILLERE_ANGLE_RELACHE = 0.0f; //the angle the release servo is for letting go when about to climb
  }

  public interface Manette
  {
	public static final int MANETTE = 0;
	public static final int BOUTON_A = 1;
	public static final int BOUTON_B = 2;
	public static final int BOUTON_X = 3;		
	public static final int BOUTON_Y = 4;
	public static final int BOUTON_GAUCHE = 5; 
	public static final int BOUTON_DROIT = 6;
	public static final int BOUTON_RETOUR = 7;
    public static final int BOUTON_DEMARRER = 8;

    public static final double SEUIL_ZERO = 0.02;
    public static final double SEUIL_AXES_OPPOSES = 0.3;

    public static final int MAIN_GAUCHE_AXE_Y = 1;
    public static final int MAIN_GAUCHE_AXE_X = 0;
    public static final int MAIN_DROITE_AXE_Y = 5;
    public static final int MAIN_DROITE_AXE_X = 4;
  }

  public interface Camera
  {
    public static final int IMAGE_PAR_SECONDE = 30;
    public static final int LARGEUR = 320;
    public static final int HAUTEUR = 240;
  }

  public interface Roues
  {
    public static final int ROUE_GAUCHE_AVANT = 1;
    public static final int ROUE_GAUCHE_ARRIERE = 2;
    public static final int ROUE_DROITE_ARRIERE = 3;  
    public static final int ROUE_DROITE_AVANT = 4;  
  }

  public interface Capteurs
  {
    public static final int CAPTEUR_ULTRA_SON_DROIT = 1;
    public static final int CAPTEUR_ULTRA_SON_GAUCHE = 2;
  }

}
