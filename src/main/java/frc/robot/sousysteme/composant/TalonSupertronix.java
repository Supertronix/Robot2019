package frc.robot.sousysteme.composant;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSupertronix extends TalonSRX
{
	public int ERREUR_DISTANCE_PERMISE = 5;

	public TalonSupertronix(int numero) {
		super(numero);
		this.configFactoryDefault();
		this.configContinuousCurrentLimit(15, 10);
		this.setNeutralMode(NeutralMode.Brake);	  	  
		this.setSensorPhase(true);
		//this.set(ControlMode.PercentOutput,0);
		this.getSensorCollection().setQuadraturePosition(0, 10);
		//this.configNeutralDeadband(0.001, 10);
	}
	
	public TalonSupertronix(int numero, boolean inversion) {
		super(numero);
		this.configFactoryDefault();
		this.setNeutralMode(NeutralMode.Brake);	  	  
		this.setSensorPhase(true);
		//this.set(ControlMode.PercentOutput,0);
		this.getSensorCollection().setQuadraturePosition(0, 10);
		this.setInverted(inversion);
		this.configNeutralDeadband(0.001, 10);
	}
	
	public void activerEncodeur()
	{
		  this.setSelectedSensorPosition(0);
		  this.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);	  
		  //this.configSelectedFeedbackCoefficient(1, 0, 10);
	}

	public void initialiserPID(double p, double i, double d)
	{
		  this.configAllowableClosedloopError(0, 0,  this.ERREUR_DISTANCE_PERMISE);
		  this.config_kP(0, p, 10);
		  this.config_kI(0, i, 10);	  	  
		  this.config_kD(0, d, 10);	
		  //this.configClosedLoopPeriod(0, 1);
	}		
	
	public void activerMinirupteur()
	{
		  this.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		  this.configClearPositionOnLimitR(true, 0);	
	}
	
	public void suivre(TalonSupertronix talon)
	{
		this.follow(talon); 
		//this.set(ControlMode.Follower, talon.getDeviceID());
	}
	
	public void synchroniser(TalonSupertronix talon)
	{
		
	}
	
	public void initialiserPIDAuxiliaire(double p, double i, double d)
	{
		//this.configAllowableClosedloopError(1, 0,  this.ERREUR_DISTANCE_PERMISE);
		this.config_kP(1, p, 10);
		this.config_kI(1, i, 10);	  	  
		this.config_kD(1, d, 10);	
		//this.configClosedLoopPeriod(1, 1);
		//this.configClosedLoopPeakOutput(1, 1, 10);
	}		

}
