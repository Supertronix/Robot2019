package frc.robot;

public class Journal {
	
	public interface NIVEAU
	{
		public static int NOTIFICATION = 1;
		public static int DETAIL = 2;
		public static int ROULEMENT = 3;
		public static int AVERTISSEMENT = 4;
		public static int ERREUR = 5;
	}
	
	protected static int niveau = 0;
	public static void activerNiveau(int niveau)
	{
		Journal.niveau = niveau;
	}
	public static void ecrire(int niveau,String message)
	{
		if(niveau >= Journal.niveau) System.out.println(message);
	}
	
	protected static boolean actif = false;
	
	public static void activer() {Journal.actif = true;}
	public static void desactiver() {Journal.actif = false;}
	
	public static void ecrire(String message)
	{
		if(Journal.actif) System.out.println(message);
	}
}
