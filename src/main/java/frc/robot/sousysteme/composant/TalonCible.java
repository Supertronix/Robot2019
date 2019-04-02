package frc.robot.sousysteme.composant;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;

class TalonCible implements PIDOutput
{
	  private TalonSRX talon;
	  @SuppressWarnings("unused")
	public TalonCible(TalonSRX talon)
	  {
		  this.talon = talon;
	  }
	@Override
	public void pidWrite(double valeur) {
		this.talon.set(ControlMode.Position,valeur);
	}
	  
}
