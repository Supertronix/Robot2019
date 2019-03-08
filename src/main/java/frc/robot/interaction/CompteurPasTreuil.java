package frc.robot.interaction;

import edu.wpi.first.wpilibj.DigitalInput;

public class CompteurPasTreuil {
	
	protected DigitalInput detecteur = null;
	protected int pas = 0;
	protected int tour = 0;
	
	public CompteurPasTreuil()
	{
		this.detecteur = new DigitalInput(3);
	}
	
	public void initialiser()
	{
		this.pas = 0;
	}
	
	public boolean estDetecte()
	{
		return !this.detecteur.get();
	}

	protected boolean estAllume = false;
	public void detecter()
	{
		if(!this.estAllume && this.detecteur.get())
		{
			this.estAllume = true;
			this.pas++;
		}
		if(this.estAllume && !this.detecteur.get())
		{
			this.estAllume = false;
		}
		//if((this.pas % 6) == 0)
		//{
		//	this.pas = 0;
		//	this.tour++;
		//}
	}
	public int getTour()
	{
		return this.tour;
	}
	public int getPas()
	{
		return this.pas;
	}
	
}
