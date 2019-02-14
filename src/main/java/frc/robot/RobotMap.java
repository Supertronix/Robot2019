package frc.robot;

public interface RobotMap {
  
  public interface Attrapeur
  {
    public static final int MOTEUR_ATTRAPEUR = 3;
    public static final float VITESSE_OUVERTURE = -0.5f;
    public static final float VITESSE_FERMETURE = 0.5f;
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

    public static final double SEUIL_ZERO = 0.1;
    public static final double SEUIL_AXES_OPPOSES = 0.3;

    public static final int MAIN_GAUCHE_AXE_Y = 1;
    public static final int MAIN_GAUCHE_AXE_X = 0;
    public static final int MAIN_DROITE_AXE_Y = 5;
    public static final int MAIN_DROITE_AXE_X = 4;

    //public static final int CONDUITE_X_GAUCHE = 0;
		//public static final int CONDUITE_Y_GAUCHE = 1;
		//public static final int CONDUITE_X_DROITE = 4;
		//public static final int CONDUITE_Y_DROITE = 5;

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
