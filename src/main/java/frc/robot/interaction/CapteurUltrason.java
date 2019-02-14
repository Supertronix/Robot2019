package frc.robot.interaction;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CapteurUltrason implements RobotMap.Capteurs
{


    // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/AnalogInput.html
    protected AnalogInput capteurUltrasonDroit;
    protected AnalogInput capteurUltrasonGauche;
    public CapteurUltrason()
    {
        this.capteurUltrasonDroit = new AnalogInput(CAPTEUR_ULTRA_SON_DROIT);
        this.capteurUltrasonGauche = new AnalogInput(CAPTEUR_ULTRA_SON_GAUCHE);            
    }

    public int getDistance()
    {
        System.out.println("Ultrason " + this.capteurUltrasonDroit.getValue());
        http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/smartdashboard/SmartDashboard.html
        SmartDashboard.putNumber("Ultrason droit", this.capteurUltrasonDroit.getValue());
        SmartDashboard.putNumber("Ultrason gauche", this.capteurUltrasonGauche.getValue());
        return this.capteurUltrasonDroit.getValue();
    }


}