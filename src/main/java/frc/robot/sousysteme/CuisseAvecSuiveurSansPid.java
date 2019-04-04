package frc.robot.sousysteme;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CuisseAvecSuiveurSansPid extends Cuisse {

	  public void fixerPosition()
	  {
		  System.out.println("Cuisse.fixerPosition()");
		  this.moteurPrincipal.set(ControlMode.Position, this.lirePositionPrincipale());
		  if(!this.modeSuiveux) this.moteurSecondaire.set(ControlMode.Position, this.lirePositionSecondaire());
	  }
		float facteur = 0.8996655518394649f; // pour C2
		public void allerVersPositionCible()
		{
			System.out.println("Cuisse.allerVersPositionCible() - " + this.positionCible);
			float deltaPrincipal =  (this.positionCible - (float)this.lirePositionPrincipale());
			if(deltaPrincipal > 0) // this.monter(0.1f)
			{
				System.out.println("deltaPrincipal = " + deltaPrincipal);
				// ratio 0.8996655518394649
				// 0.5f, 0.8f //0.9f // 0.7f
				// 0.45f, 0.72f //0.81f //0.63f
				if(deltaPrincipal < 300)
				{
					this.moteurPrincipal.set(ControlMode.PercentOutput, (deltaPrincipal/100)*0.3f*facteur); 				
					if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, (deltaPrincipal/100)*0.3f); 
				}
				else if(deltaPrincipal < 400)
				{
					this.moteurPrincipal.set(ControlMode.PercentOutput, 0.4f*facteur); 				
					if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.4f); 
				}
				else
				{
					this.moteurPrincipal.set(ControlMode.PercentOutput, 0.6*facteur); 
					if(this.moteurSecondaireActif)this.moteurSecondaire.set(ControlMode.PercentOutput, 0.6f); 
				}
			}
			else // on ne replie jamais
			{
				
			}
			//float deltaSecondaire =  ((float)this.lirePositionSecondaire() - this.positionCible);//todo
		}
		protected float positionCible = 0.0f;

		public float getPositionCible()
		{
	        System.out.println("Cuisse.getPositionCible() : la position cible est " + this.positionCible);
			return this.positionCible;
		}
		public void positionner(float position)
		{
			this.positionCible = position;
			System.out.println("Cuisse.positionner() : la nouvelle position desiree est " + this.positionCible);
		}
		public void incrementerPosition(float incrementPosition)
		{
			this.positionCible = (float) (this.lirePositionPrincipale() + incrementPosition);
			System.out.println("Cuisse.incrementerPosition() : la nouvelle position desirée est " + this.positionCible);
		}
		protected int distanceRestante;
		public boolean estArrivePositionCible()
		{
			System.out.println("Cuisse.estArrivePositionCible()");
			this.distanceRestante = (int)(this.positionCible - lirePositionPrincipale());
			System.out.println("Distance restante cuisse " + this.distanceRestante);
			if (this.distanceRestante < 10) return true; // cibles toujours positives apres homing
			if(this.position >= this.POSITION_MAX) return true;	
			return false;
		}

}
