package frc.robot.interaction;

import frc.robot.Journal;
import frc.robot.commande.attrapeur.CommandeAnnoncerAttrapage;
import frc.robot.commande.attrapeur.CommandeDesactiverAnnonceAttrapage;

public class AnimateurDisque {
	
	protected DetecteurEcoutilleAttrapee detecteurEcoutille;
	protected CommandeAnnoncerAttrapage annonce;  
	protected CommandeDesactiverAnnonceAttrapage annulation;
	protected boolean annoncePubliee = false;
	protected boolean estAttrape = false;

	public AnimateurDisque(DetecteurEcoutilleAttrapee detecteurEcoutille)
	{
		this.detecteurEcoutille = detecteurEcoutille;
	}
	
	public void animerSelonSignal()
	{
		//Journal.ecrire(Journal.NIVEAU.NOTIFICATION,"Droit : " + this.detecteurEcoutille.estDetecteCoteDroit());
		//Journal.ecrire(Journal.NIVEAU.NOTIFICATION,"Gauche : " + this.detecteurEcoutille.estDetecteCoteGauche());
		this.estAttrape = this.detecteurEcoutille.estAttrapee();
		//Journal.ecrire(Journal.NIVEAU.DETAIL,"EST ATTRAPE = " + estAttrape);
		if(this.estAttrape && !annoncePubliee)
		{
			Journal.ecrire("estAttrape & !annoncePubliee");
			annonce = new CommandeAnnoncerAttrapage();
			annonce.start();
			annoncePubliee = true;
			//annulation.close();
		}
		if(!this.estAttrape && annoncePubliee)
		{
			Journal.ecrire("! estAttrape & annoncePubliee");
			annulation = new CommandeDesactiverAnnonceAttrapage();
			annoncePubliee = false;
			//annonce.close();
			annulation.start();
		}

	}

}
