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
        
        // http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/smartdashboard/SmartDashboard.html
        SmartDashboard.putNumber("Ultrason droit", this.capteurUltrasonDroit.getValue());
        SmartDashboard.putNumber("Ultrason gauche", this.capteurUltrasonGauche.getValue());
        
        // https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599718-analog-inputs
        // double averageVolts = exampleAnalog.getAverageVoltage();
        SmartDashboard.putNumber("Ultrason droit en volatge moyen", this.capteurUltrasonDroit.getAverageVoltage());
        SmartDashboard.putNumber("Ultrason gauche  en volatge moyen", this.capteurUltrasonGauche.getAverageVoltage());
        
        
        return this.capteurUltrasonDroit.getValue();
    }
    
    public double getDistanceGauche()
    {
        //System.out.println("Ultrason gauche en volatge moyen " + this.capteurUltrasonGauche.getAverageVoltage());
        //SmartDashboard.putNumber("Ultrason gauche en volatge moyen", this.capteurUltrasonGauche.getAverageVoltage());
    	return this.calculerDistanceAvecVoltage(this.capteurUltrasonGauche.getAverageVoltage());
    }

    public double getDistanceDroit()
    {
        //System.out.println("Ultrason droit en volatge moyen " + this.capteurUltrasonDroit.getAverageVoltage());
        //SmartDashboard.putNumber("Ultrason droit en volatge moyen", this.capteurUltrasonDroit.getAverageVoltage());
    	return this.calculerDistanceAvecVoltage(this.capteurUltrasonDroit.getAverageVoltage());
    }

    // ultrasonic 
    // 11"	4V
    // 10"	3,9V
    //  9"	3,4V
    //  8"	2,9V
    //  7"	2,4V
    //  6"	1,9V
    //  5"	1,4V
    private double calculerDistanceAvecVoltage(double voltage)
    {
    	double distance = 0.0;
    	
    	if(voltage < 4.0)
    	{
    		distance = calculerDistance(voltage);
    	}
    	
    	return distance;
    	
    }
    
    // x = voltage
    // y = distance
    private double calculerDistance(double voltage)
    {
    	// return  2.0 * voltage + 1.282;
    	return  2.0 * voltage + 2.2;
    	    	
    }
    
}