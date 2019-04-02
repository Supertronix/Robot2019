package frc.robot.sousysteme.composant;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

class EncodeurPrincipalSource implements PIDSource
{
	TalonSRX talon = null;
	@SuppressWarnings("unused")
	public EncodeurPrincipalSource(TalonSRX talon) 
	{
		  this.talon = talon;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return  PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return this.talon.getSelectedSensorPosition();
	}
}
