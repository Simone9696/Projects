package it.unibs.ing.fp.tamagotchi;

import it.unibs.fp.mylib.InputDati;

/**
 * Classe di supporto per i metodi di stampa di brevi messaggi all'utente.
 * @author Simone Macobatti, Vipandeep Singh
 *
 */
public class SalutiFrasi {
	
	private static final String ATTENZIONE_CARATTERE_NON_VALIDO = "ATTENZIONE: carattere non valido! Riprova";

	private static final String VUOI_GIOCARE_ANCORA = "Vuoi giocare ancora? ";

	private static final String DECORATOR = "_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
	
	private static final String SALUTO_INIZIALE = " \t\tCiao! Benvenuto nel programma di simulazione\n"
													+ " \t\tdi TAMAGOTCHI. Nel seguito ti verra' chiesto\n"
													+ " \t\tdi inserire un nome per il tuo Tamagotchi, un\n"
													+ " \t\tgrado di sazieta' iniziale ed un grado di \n"
													+ " \t\tsoddisfazione affettiva iniziale.";
	
	private static final String SALUTO_FINALE = "\t\tGrazie per aver giocato a Tamagotchi. Torna presto!";
	
	private static final String MORTO = "\nMi spiace il tuo Tamagotchi è morto :(\n";
	
	private static final String RICOMINCIA = "\nOra puoi creare un nuovo Tamagotchi!\n";
	
	private static final String ARRIVEDERCI = "\nNon possiamo obbligarti a rimanere...\n";
	
	private static final String MESSAGGIO_USCITA = "Per continuare premere 'C'\n"
													+ "Per interrompere il programma premere 'Q'";
	
	
			
	/**
	 * Mostra un messaggio di benvenuto
	 */
	public static void salutoIniziale() {
		System.out.println();
		System.out.println(DECORATOR);
		System.out.println();
		System.out.println(SALUTO_INIZIALE);
		System.out.println();
		System.out.println(DECORATOR);
	}
	
	/**
	 * Mostra un messaggio di chiusura
	 */
	public static void salutoFinale() {
		System.out.println();
		System.out.println(DECORATOR);
		System.out.println();
		System.out.println(SALUTO_FINALE);
		System.out.println();
		System.out.println(DECORATOR);
		
	}
	
	/**
	 * Avvisa l'utente che il suo Tamagotchi e' morto
	 */
	public static void stampaMorto() {
		System.out.println(MORTO);
	}
	
	/**
	 * Chiede all'utente se desidera giocare ancora
	 * @return true se vuole giocare ancora, false altrimenti
	 */
	public static boolean giocaAncora() {
		
		return InputDati.yesOrNo(VUOI_GIOCARE_ANCORA);

	}
	
	/**
	 * Avvisa l'utente che puo' creare un nuovo Tamagotchi
	 */
	public static void ricomincia() {
		System.out.println(RICOMINCIA);
		
	}
	
	/**
	 * Stampa un messaggio di arrivederci
	 */
	public static void arrivederci() {
		System.out.println(ARRIVEDERCI);
		
	}
	
	/**
	 * Chiede all'utente se desidera continuare o interrompere il programma
	 * @return true se vuole interrompere, false se vuole continuare
	 */
	public static boolean vuoiUscire() {
		
		while(true) {
			char temp = InputDati.leggiChar(MESSAGGIO_USCITA);
			if (temp == 'q' || temp == 'Q') return true;
			if (temp == 'c' || temp == 'C') return false;
			System.out.println(ATTENZIONE_CARATTERE_NON_VALIDO);
		}
	}
	
}
