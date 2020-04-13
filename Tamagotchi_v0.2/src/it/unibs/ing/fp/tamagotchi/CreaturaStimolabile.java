package it.unibs.ing.fp.tamagotchi;

/**
 * Classe astratta da cui eredita Tamagotchi in vista di una eventuale futura espansione dove ci potranno essere
 * pi� possibilit� di stimolo e/o altre creature stimolabili oltre i Tamagotchi.
 * @author Simone Macobatti
 *
 */
public abstract class CreaturaStimolabile {

	public abstract void setBenessere();
	
	public abstract boolean isMorto();
	
	public abstract boolean riceviBiscotti(int biscotti);

	public abstract boolean riceviCarezze(int carezze);

	
}
