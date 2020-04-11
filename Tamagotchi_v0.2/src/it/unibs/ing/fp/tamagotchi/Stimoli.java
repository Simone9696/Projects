package it.unibs.ing.fp.tamagotchi;

import java.util.Random;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

/**
 * Classe che mette a disposizione metodi statici per gestire la fase di stimolo dei Tamagotchi sulla base
 * dei metodi di ricezione messi a disposizione da quest'ultimi. Ogni Tamagotchi (e anche eventuali sue future
 * sottoclassi) ereditano da CreaturaStimolabile; siamo dunque sicuri di avere a disposizione un certo 
 * set di metodi di ricezione.
 * @author Simone
 *
 */
public class Stimoli {
	
		
	private static final String SCEGLI_LO_STIMOLO_CHE_VUOI_FORNIRE = "\nScegli lo stimolo che vuoi fornire";

	private static final String SCELTA_STIMOLO = "\nE' ora di dare un po' di attenzioni al tuo %s Tama!\n"
												+ "Puoi scegliere se accarezzarlo o dargli dei biscotti.\n\n";
	
	private static final String QUANTE_CAREZZE = "\nQuante carezze vuoi dare al tuo Tamagotchi? ";
	
	private static final String QUANTI_BISCOTTI = "\nQuanti biscotti vuoi dare al tuo Tamagotchi? ";
	
	// private static int numeroStimoli = 0; // scommentare se servisse un contatore di stimoli globale
	
	private static final String elencoStimoli[] = {"BISCOTTI", "CAREZZE"};

	
	/**
	 * Chiede all'utente quante carezze dare al Tamagotchi
	 * @return il numero di carezze
	 */
	private static int quanteCarezze() {
		
		return InputDati.leggiIntero(QUANTE_CAREZZE);
	}
	
	/**
	 * Chiede all'utente quanti biscotti dare al Tamagotchi
	 * @return il numero di biscotti
	 */
	private static int quantiBiscotti() {
		
		return InputDati.leggiIntero(QUANTI_BISCOTTI);
	}
	
	/**
	 * Permette all'utente di stimolare un Tamagotchi in modo guidato.
	 * @param tama il Tamagotchi da stimolare
	 * @param ordinale primo, secondo, terzo, ecc.
	 */
	public static void stimolaTama(Tamagotchi tama, String ordinale) {
		
		int stimolo = sceltaStimolo(true, ordinale);
		
		switch (stimolo) {
		
		case 1:	while(!(tama.riceviBiscotti(quantiBiscotti()))) {
						stimolo = sceltaStimolo(false, ordinale);
					};	break;
					
		case 2:	while(!(tama.riceviCarezze(quanteCarezze()))) {
						stimolo = sceltaStimolo(false, ordinale);
					};	break;
					
		default:		break;
		
		}	
	}
	
	/**
	 * Permette all'utente di stimolare un Tamagotchi in modo casuale.
	 * @param tama il Tamagotchi da stimolare
	 * @param ordinale primo, secondo, terzo, ecc.
	 */
	public static void stimolaTamaCasuale(Tamagotchi tama, String ordinale) {
		
		int stimolo = sceltaStimolo(true, ordinale);
		
		Random rd = new Random();
		
		switch (stimolo) {
		
		case 1:	while(!(tama.riceviBiscotti(rd.nextInt(Tamagotchi.getMaxBiscotti()) + 1))) {
						stimolo = sceltaStimolo(false, ordinale);
					};	break;
					
		case 2:	while(!(tama.riceviCarezze(rd.nextInt(Tamagotchi.getMaxCarezze()) + 1))) {
						stimolo = sceltaStimolo(false, ordinale);
					};	break;
					
		default:		break;
		
		}	
	}
	

	/**
	 * Permette all'utente di scegliere lo stimolo che desidera fornire al Tamagotchi e la quantita' associata
	 * @param intro se posto a true stampa un ulteriore messaggio di introduzione alla scelta
	 * @return il carattere corrispondente allo stimolo scelto
	 */
	private static int sceltaStimolo(boolean intro, String ordinale) {

		if (intro) System.out.printf(SCELTA_STIMOLO, ordinale);
		
		MyMenu menu = new MyMenu(SCEGLI_LO_STIMOLO_CHE_VUOI_FORNIRE, elencoStimoli);
		
		return menu.scegli();
		
	}

}
