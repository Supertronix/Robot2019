package frc.robot.sousysteme;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.interaction.DetecteurEcoutilleAttrapee;
import frc.robot.interaction.CompteurPasTreuil;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



// Aussi appelé Winches par l'équipe	
public class Treuil extends Subsystem implements RobotMap.Attrapeur.Treuil{

	// http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
	protected TalonSRX moteur = new TalonSRX(this.MOTEUR_TREUIL);
	protected CompteurPasTreuil detecteurPas;
	
			
  public Treuil()
  {
	  this.moteur.setNeutralMode(NeutralMode.Brake);	
	  this.detecteurPas = new CompteurPasTreuil();
  }
  
  @Override
  public void initDefaultCommand() {}
  
  public void monter()
  {
	this.moteur.set(ControlMode.PercentOutput, 0.1);
  }
  public void monter(float vitesse)
  {
	this.moteur.set(ControlMode.PercentOutput, vitesse);
  }

  
}
