package it.unibs.ing.fp.tamagotchi;

/**
 * 
 * @author Simone Macobatti, Vipandeep Singh, Karim Boudarraja
 *
 */
public class TamaMain {

	private static boolean rigenera = false;
	
	public static void main(String[] args) {
		
		SalutiFrasi.salutoIniziale();
		Tamagotchi tama1 = Tamagotchi.inizializzaTama();
		System.out.println(tama1.toString());
		
		while (true) {	
			
			if (rigenera) {
				tama1 = Tamagotchi.inizializzaTama();
				System.out.println(tama1.toString());
				setRigenera(false);
			}
			
			if (faseDiGioco(tama1, "primo")) {
				break;
			}	
		}
		
		SalutiFrasi.salutoFinale();
		
	}

	/**
	 * Gestisce una fase di gioco
	 * @param tama l'oggetto Tamagotchi con cui giocare
	 * @return ritorna true se l'utente vuole interrompere il gioco, false se vuole continuare o rigiocare
	 */
	private static boolean faseDiGioco(Tamagotchi tama, String ordinale) {
		
		Stimoli.stimolaTamaCasuale(tama, ordinale);
		
		if (tama.isMorto()) {
			
			SalutiFrasi.stampaMorto();
			
			if (SalutiFrasi.giocaAncora()) {
				
				SalutiFrasi.ricomincia();
				setRigenera(true);
				return false;	
			} else {
				
				SalutiFrasi.arrivederci();
				return true;
			}
		}
		
		System.out.println(tama.toString());
		
		return SalutiFrasi.vuoiUscire();
		
	}
	
	public static boolean getRigenera() {
		return rigenera;
	}

	public static void setRigenera(boolean rigenera) {
		TamaMain.rigenera = rigenera;
	}
	
}
