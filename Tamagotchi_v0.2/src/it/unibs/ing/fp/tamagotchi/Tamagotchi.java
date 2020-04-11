package it.unibs.ing.fp.tamagotchi;

import it.unibs.fp.mylib.InputDati;

/**
 * Classe istanziabile che implementa le creature Tamagotchi.
 * @author Simone
 *
 */
public class Tamagotchi extends CreaturaStimolabile{
	
	
	protected String nome;
	protected int soddis_aff;
	protected int sazieta;
	protected boolean felice;
	protected boolean infelice;
	protected int carezze_ricevute;
	protected int biscotti_ricevuti;
	
	private static final int MAX_SAZIETA = 100;
	private static final int MAX_SODDISFAZIONE = 100;
	private static final int MIN_SAZIETA = 0;
	private static final int MIN_SODDISFAZIONE = 0;
	private static final int SOGLIA_SODD_INF = 30;
	private static final int SOGLIA_SAZIETA_INF = 30;
	private static final int SOGLIA_SODD_SUP = 80;
	private static final int SOGLIA_SAZIETA_SUP = 80;
	private static final int TROPPO_PIENO = 90;
	private static final int MAX_CAREZZE = 20;
	private static final int MAX_BISCOTTI = 20;
	//private static final int PENALTY_SAZIETA = 5;
	//private static final int PENALTY_SODDISFAZIONE = 5;
	private static final int MAX_SAZIETA_INIZIALE = 80;
	private static final int MIN_SAZIETA_INIZIALE = 20;
	private static final int MAX_SODDISFAZIONE_INIZIALE = 80;
	private static final int MIN_SODDISFAZIONE_INIZIALE = 20;
	private static final double SOGLIA_AUMENTO_SAZIETA = 20;
	private static final double TASSO_EFFICACIA_BISCOTTI = 1.1; // Ogni biscotto aumenta la sazietà del 10%
	
	private static final String INSERISCI_SODDISFAZIONE = "Inserisci un valore compreso tra "+MIN_SODDISFAZIONE_INIZIALE+" e "+MAX_SODDISFAZIONE_INIZIALE+" per "
													+ "il grado di soddisfazione iniziale: ";
	private static final String INSERISCI_SAZIETA = "Inserisci un valore compreso tra "+MIN_SAZIETA_INIZIALE+" e "+MAX_SAZIETA_INIZIALE+" per il grado "
													+ "di sazieta' iniziale: ";
	private static final String INSERISCI_NOME = "\nInserisci il nome per il tuo Tamagotchi: ";
	private static final String AVVISO_MAX_CAREZZE = "\nAttenzione: massimo " + MAX_CAREZZE + " carezze!";
	private static final String AVVISO_MAX_BISCOTTI = "\nAttenzione: massimo " + MAX_BISCOTTI + " biscotti!";
	private static final String AVVISO_MAX_SAZIETA = "\nMassimo valore di sazieta' raggiunto";
	private static final String AVVISO_MAX_SODDISFAZIONE = "\nMassimo valore di soddisfazione raggiunto!";
	private static final String BISCOTTI_POCO_EFFETTO = "\nI biscotti hanno poco effetto...";
	private static final String BISCOTTI_MOLTO_EFFETTO = "\nI biscotti hanno molto effetto!";
	
	
	
	
	public Tamagotchi(String nome, int soddis_aff, int sazieta) {
		
		if (soddis_aff < 0 || sazieta < 0) throw new IllegalArgumentException();
		this.setSoddis_aff(soddis_aff);
		this.setSazieta(sazieta);
		this.setNome(nome);
		this.setBiscotti_ricevuti(0);
		this.setCarezze_ricevute(0);
		this.setBenessere();
	}
	
	/**
	 * Permette di inizializzare un nuovo Tamagotchi chiedendo all'utente diversi parametri
	 * @return l'oggetto Tamagotchi appena creato
	 */
	public static Tamagotchi inizializzaTama() {
		
		String nome = InputDati.leggiStringaNonVuota(INSERISCI_NOME);
		int soddisfazione = InputDati.leggiIntero(INSERISCI_SODDISFAZIONE, MIN_SODDISFAZIONE_INIZIALE, MAX_SODDISFAZIONE_INIZIALE);
		int sazieta = InputDati.leggiIntero(INSERISCI_SAZIETA, MIN_SAZIETA_INIZIALE, MAX_SAZIETA_INIZIALE);
		
		return new Tamagotchi(nome, soddisfazione, sazieta);
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer stringa = new StringBuffer();
		stringa.append("\nINFORMAZIONI SUL TUO TAMAGOTCHI\n");
		if (this.isFelice()) stringa.append("\nIl tuo Tamagotchi è felice!\n");
		if (this.isInfelice())stringa.append("\nIl tuo Tamagotchi NON è felice :(\n"); 
		stringa.append("\nNome:\t\t\t" + this.getNome());
		stringa.append("\nSoddisfazione\t\t" + this.getSoddis_aff() + "\t(Max " + MAX_SODDISFAZIONE + ")");
		stringa.append("\nSazieta'\t\t" + this.getSazieta() + "\t(Max " + MAX_SAZIETA + ")");
		stringa.append("\nCarezze ricevute\t" + this.getCarezze_ricevute());
		stringa.append("\nBiscotti ricevuti\t" + this.getBiscotti_ricevuti() + "\n");
		
		return stringa.toString();
	}

	/**
	 * Quando viene chiamato imposta i valori booleani di felicita'/infelicita' del Tamagotchi
	 */
	@Override
	public void setBenessere() {
		
		if (this.soddis_aff < SOGLIA_SODD_INF || this.sazieta < SOGLIA_SAZIETA_INF || this.sazieta > TROPPO_PIENO) {
			this.setInfelice(true);
			this.setFelice(false);
		} else if (this.soddis_aff > SOGLIA_SODD_SUP && this.sazieta > SOGLIA_SAZIETA_SUP) {
			this.setInfelice(false);
			this.setFelice(true);
		} else {
			this.setInfelice(false);
			this.setFelice(false);
		}
		
	}
	
	/**
	 * Determina se il Tamagotchi è vivo o morto
	 * @return true se e' morto, false se e' vivo
	 */
	@Override
	public boolean isMorto() {
		
		if (getSoddis_aff() <= MIN_SODDISFAZIONE || getSazieta() <= MIN_SAZIETA || getSazieta() == MAX_SAZIETA) {
			return true;
		} return false;
		
	}
	
	// METODI PER CONGRUENZA TEST
	
	public boolean sonoMorto() {
		return isMorto();
	}
	
	public boolean sonoTriste() {
		return isInfelice();
	}
	
	// FINE METODI PER CONGRUENZA TEST
	
	/**
	 * Permette di dare dei biscotti al Tamagotchi aumentando il suo grado di sazieta'. Se viene superato il
	 * numero massimo di biscotti che si possono dare in una volta viene stampato un messaggio di errore.
	 * @param biscotti il numero di biscotti da dare
	 * @return true se il numero di biscotti rientra nel massimo consentito, false altrimenti
	 */
	@Override
	public boolean riceviBiscotti(int biscotti) {
		
		
		if (biscotti <= MAX_BISCOTTI) {
			
			
			int temp = getSazieta();
			double nuovo = temp;
			for (int i = 0; i < biscotti; i++)
				nuovo = nuovo*TASSO_EFFICACIA_BISCOTTI;
			if (nuovo - temp > SOGLIA_AUMENTO_SAZIETA)
				nuovo = temp + SOGLIA_AUMENTO_SAZIETA;
			if (setSazieta((int) nuovo)) { // solo se i biscotti hanno effetto...
				setBiscotti_ricevuti(getBiscotti_ricevuti() + biscotti); // aggiungi i biscotti ricevuti
				setSoddis_aff(getSoddis_aff() - biscotti/4); // aggiungi penalita'
				setBenessere();
			}
			
			if (getSazieta() - temp < MAX_BISCOTTI/2) {
				System.out.println(BISCOTTI_POCO_EFFETTO);
			} else {
				System.out.println(BISCOTTI_MOLTO_EFFETTO);
			}
			return true;
		} 
		
		System.out.println(AVVISO_MAX_BISCOTTI);
		return false;
		
		
	}
	
	/**
	 * Fornisce carezze al Tamagotchi aumentando il suo grado di soddisfazione. Se viene superato il numero
	 * massimo di carezze che si possono dare in una volta viene stampato un messaggio di errore.
	 * @param carezze il numero di carezze da dare
	 * @return true se il numero di carezze rientra nel massimo consentito, false altrimenti
	 */
	@Override
	public boolean riceviCarezze(int carezze) {

		if (carezze <= MAX_CAREZZE) {
			
			int temp = getSoddis_aff();
			if (setSoddis_aff(getSoddis_aff() + carezze)) { // solo se le carezze hanno effetto...
				setCarezze_ricevute(getCarezze_ricevute() + getSoddis_aff() - temp); // aggiungi le carezze ricevute
				setSazieta(getSazieta() - carezze/2); // aggiungi penalita'
			}
			setBenessere();
			return true;
		} 
		
		System.out.println(AVVISO_MAX_CAREZZE);
		return false;
		}
	
	
	//**************************
	// SEGUONO GETTERS E SETTERS
	//**************************
	
	

	public int getSoddis_aff() {
		return soddis_aff;
	}
	
	// il compito di controllare se la soddisfazione va sotto zero è demandato al metodo isMorto()
	// che verra' opportunamente chiamato nel main
	
	/**
	 * Permette di settare il grado di soddisfazione affettiva
	 * @param soddis_aff il valore della soddisfazione affettiva
	 * @return ritorna true se il valore settato è diverso dal precedente, false altrimenti
	 */
	public boolean setSoddis_aff(int soddis_aff) {
		
		int temp = getSoddis_aff();
		
		if (soddis_aff > MAX_SODDISFAZIONE) {
			this.soddis_aff = MAX_SODDISFAZIONE;
			System.out.println(AVVISO_MAX_SODDISFAZIONE);
		} else {
			this.soddis_aff = soddis_aff;
		}
		
		if (getSoddis_aff() == temp) return false;
		
		return true;
		
	}

	public int getSazieta() {
		return sazieta;
	}
	
	// il compito di controllare se la sazieta' va sotto zero è demandato al metodo isMorto()
	// che verra' opportunamente chiamato nel main
	
	/**
	 * permette di settare il grado di sazieta'
	 * @param sazieta il grado di sazieta' da settare
	 * @return ritorna true se il valore settato è diverso dal precedente, false altrimenti
	 */
	public boolean setSazieta(int sazieta) {
		
		int temp = getSazieta();
		
		if (sazieta > MAX_SAZIETA){
			this.sazieta = MAX_SAZIETA;
			System.out.println(AVVISO_MAX_SAZIETA);
		} else {
			this.sazieta = sazieta;
		}
		
		if (getSazieta() == temp) return false;
		
		return true;
	}
	
	public int getCarezze_ricevute() {
		return carezze_ricevute;
	}
	
	public void setCarezze_ricevute(int carezze_ricevute) {
		this.carezze_ricevute = carezze_ricevute;
	}
	
	public int getBiscotti_ricevuti() {
		return biscotti_ricevuti;
	}
	
	public void setBiscotti_ricevuti(int biscotti_ricevuti) {
		this.biscotti_ricevuti = biscotti_ricevuti;
	}

	public boolean isFelice() {
		return felice;
	}

	public void setFelice(boolean felice) {
		this.felice = felice;
	}

	public boolean isInfelice() {
		return infelice;
	}

	public void setInfelice(boolean infelice) {
		this.infelice = infelice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the maxCarezze
	 */
	public static int getMaxCarezze() {
		return MAX_CAREZZE;
	}

	/**
	 * @return the maxBiscotti
	 */
	public static int getMaxBiscotti() {
		return MAX_BISCOTTI;
	}

	
}
