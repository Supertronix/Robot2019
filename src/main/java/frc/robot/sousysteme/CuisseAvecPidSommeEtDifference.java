package frc.robot.sousysteme;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;

import frc.robot.sousysteme.composant.TalonSupertronix;

public class CuisseAvecPidSommeEtDifference extends Cuisse {

	public CuisseAvecPidSommeEtDifference()
	{
		super();				  
	}

	  public void configurerMoteurSecondaire() // polymorphique
	  {
		  this.moteurSecondaire.activerEncodeur();
		  this.moteurSecondaire.initialiserPID(PID_P, PID_I, 0);
		  this.moteurSecondaire.activerLimiteZero();			  
	  }

	  public void synchroniser()
	  {
		  super.synchroniser();
		  System.out.println("Auxiliaire (difference) : l'erreur est de " + this.moteurPrincipal.getClosedLoopError(1) + " sur une cible de " + this.moteurPrincipal.getClosedLoopTarget(1));
		  System.out.println("Master (moyenne): l'erreur est de " + this.moteurPrincipal.getClosedLoopError(0) + " sur une cible de " + this.moteurPrincipal.getClosedLoopTarget(0));
	  }
	  public void donnerConsignePID(float consigne) 
	  {
		  System.out.println("Cuisse.donnerConsignePID("+consigne+")");
		  this.consigne = limiterPID(consigne, POSITION_MIN, POSITION_MAX);
		  this.moteurPrincipal.set(ControlMode.Position, this.consigne, DemandType.AuxPID, 0);
		  //if(this.synchroManuelle) //this.moteurSecondaire.set(ControlMode.Position, this.consignePrincipale);		  
	  }	
	  
	  public void augmenterConsignePID(float increment) {
		  System.out.println("Cuisse.augmenterConsignePID("+increment+")");
		  
		  //this.consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) + increment, POSITION_MIN, POSITION_MAX);
		  this.consigne = limiterPID(this.consigne+ increment, POSITION_MIN, POSITION_MAX);
		  System.out.println("=====================================================");
		  System.out.println("Nouvelle consigne moteur principal " + this.consigne);
		  this.moteurPrincipal.set(ControlMode.Position, this.consigne, DemandType.AuxPID, 0);			  
	  }
	  
	  public void reduireConsignePID(float decrement) {
		  System.out.println("Cuisse.reduireConsignePID("+decrement+")");
			  //this.consigne = limiterPID(this.moteurPrincipal.getClosedLoopTarget(0) - decrement, POSITION_MIN, POSITION_MAX);
			  this.consigne = limiterPID(this.consigne - decrement, POSITION_MIN, POSITION_MAX);
			  System.out.println("Nouvelle consigne moteur principal " + this.consigne);
			  this.moteurPrincipal.set(ControlMode.Position, this.consigne, DemandType.AuxPID, 0);			  
	  }
		public void activerCalibration()
		{
			super.activerCalibration();
			this.activerEncodeurAuxiliaire();
		}
		
		
		/////////////////////////////////////////////////////////////////
		
		  public void activerEncodeurAuxiliaire()
		  {
			  //this.annulerConsigne();
				this.moteurPrincipal.configRemoteFeedbackFilter(this.moteurSecondaire.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 1, 10);
				
				this.moteurPrincipal.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor1, 10);
				this.moteurPrincipal.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
				this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 10);
				this.moteurPrincipal.configSelectedFeedbackCoefficient(0.5, 0, 10);
				this.moteurPrincipal.initialiserPID(0.8, 0.00005, 0); //	public double PID_P = 0.1; public double PID_I = 0.00099;
				this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 10);
				this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, 10);
				//this.moteurPrincipal.config_IntegralZone(1, 200, 10); // Integral Zone can be used to auto clear the integral accumulator if the sensor pos is too far from the target. This prevent unstable oscillation if the kI is too large. Value is in sensor units.
				
				this.moteurPrincipal.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor1, 10);
				this.moteurPrincipal.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 10);
				this.moteurPrincipal.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, 1, 10);
				this.moteurPrincipal.configSelectedFeedbackCoefficient(-1, 1, 10);
				this.moteurPrincipal.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, 10);
				this.moteurSecondaire.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 20, 10);
				this.moteurPrincipal.configAuxPIDPolarity(false, 10); 
				this.moteurPrincipal.initialiserPIDAuxiliaire(6, 0.1, 0); // 2 0 4
				
				this.moteurSecondaire.follow(this.moteurPrincipal, FollowerType.AuxOutput1);
				
		  }		

}
