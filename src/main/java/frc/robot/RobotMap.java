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

    public static final double SEUIL = 0.2;

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

}
