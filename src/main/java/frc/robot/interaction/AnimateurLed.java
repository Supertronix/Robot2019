package frc.robot.interaction;

import edu.wpi.first.wpilibj.DigitalOutput;
import frc.robot.RobotMap;

public class AnimateurLed implements RobotMap.Affichage{
	  protected DigitalOutput signal = null;
	  protected DetecteurEcoutilleAttrapee detecteurEcoutille = null;
	  public AnimateurLed(DetecteurEcoutilleAttrapee detecteurEcoutille)
	  {
		    this.signal = new DigitalOutput(SIGNAL_ANIMATION_LED);
		    this.detecteurEcoutille = detecteurEcoutille;
	  }
	  public void animerSelonSignal()
	  {
			if(this.detecteurEcoutille.estDetecteCoteDroit() || this.detecteurEcoutille.estDetecteCoteGauche())
			{
				this.signal.set(true);
			}
			else
			{
				this.signal.set(false);
			}

	  }

}
