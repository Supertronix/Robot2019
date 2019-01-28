package frc.robot;

public interface RobotMap {
  
  public interface Attrapeur{
    public static int MOTEUR_ATTRAPEUR = 3;
    public static float VITESSE_OUVERTURE = -0.1f;
    public static float VITESSE_FERMETURE = 0.1f;
  }

  public interface Manette{
		public static final int MANETTE = 0;
		public static final int BOUTON_A = 1;
		public static final int BOUTON_B = 2;
		public static final int BOUTON_X = 3;		
		public static final int BOUTON_Y = 4;
		public static final int BOUTON_GAUCHE = 5;
		public static final int BOUTON_DROIT = 6;
		public static final int BOUTON_RETOUR = 7;
    public static final int BOUTON_DEMARRER = 8;
  }

}
