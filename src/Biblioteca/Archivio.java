package Biblioteca;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Archivio {
	private static final Logger logger = LoggerFactory.getLogger(Archivio.class);

	static String fileName = "Archivio.txt";
	static File fileInfo = new File(fileName);
	private static final String ENCODING = "UTF-8";
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		
		boolean run = true;
		
		while (run) {
			
			ArrayList<String> archivio = new ArrayList<String>(FileUtils.readLines(fileInfo, ENCODING));
			System.out.println("");
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("*** GESTIONALE ARCHIVIO BIBLIOTECA - PREMI UN NUMERO PER SCEGLIERE L'AZIONE CORRISPONDENTE ***");
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("1. Aggiungi libro o rivista");
			System.out.println("");
			System.out.println("2. Rimuovi elemento con codice ISBN");
			System.out.println("");
			System.out.println("3. Ricerca elemento per ISBN");
			System.out.println("");
			System.out.println("4. Ricerca elemento per Anno di pubblicazione");
			System.out.println("");
			System.out.println("5. Ricerca elemento per Autore");
			System.out.println("");
			System.out.println("6. Stampa dati presenti in archivio");
			System.out.println("");
			System.out.println("0. Premi per uscire dal programma");
			System.out.println("");

			try {
				String scelta = in.nextLine();
				switch (scelta) {
				case "1":
					opzioni();
					break;
				case "2":
					System.out.println("Inserisci il codice ISBN");
					String ISBN = in.nextLine();
					archivio.removeIf(elm -> elm.contains(ISBN));
					FileUtils.writeLines(fileInfo, archivio);
					System.out.println("");
					System.out.println("L'elemento con ISBN " + ISBN + " è stato rimosso dall'archivio");
					System.out.println("");
					break;
				case "3":
					System.out.println("Inserisci il codice ISBN:");
					ISBN = in.nextLine();
					System.out.println("");
					archivio.stream().filter((elm) -> elm.contains(ISBN)).forEach((e) -> System.out.println(e));
					System.out.println("");
					break;
				case "4":
					System.out.println("Inserisci l'anno di pubblicazione:");
					System.out.println("");
					String anno = in.nextLine();
					System.out.println("");
					archivio.stream().filter((elm) -> elm.contains(anno)).forEach((e) -> System.out.println(e));
					System.out.println("");
					break;
				case "5":
					System.out.println("Inserisci l'autore:");
					System.out.println("");
					String autore = in.nextLine();
					System.out.println("");
					archivio.stream().filter((elm) -> elm.contains(autore)).forEach((e) -> System.out.println(e));
					System.out.println("");
					break;
				case "6":
					System.out.println( FileUtils.readFileToString(fileInfo, ENCODING) );
					System.out.println("");
					break;
				case "0":
					System.out.println("Sei uscito dal programma.");
					System.exit(0);
					break;
				default:
					throw new InputMismatchException("Scelta non valida.");
				}
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			}
		}

	}

	public static void opzioni() {
		while (true) {
			try {
				System.out.println("Scegli tra le seguenti opzioni: ");
				System.out.println("");
				System.out.println("1. Aggiungi libro");
				System.out.println("2. Aggiungi rivista");
				int scelta = Integer.parseInt(in.nextLine());
				if (scelta == 1) {
					nuovoLibro();
					break;
				} else if (scelta == 2) {
					nuovaRivista();
					break;
				} else {
					throw new IllegalArgumentException();
				}
			} catch (InputMismatchException e) {
				logger.error("Scelta non valida.");
				in.nextLine();
			} catch (IllegalArgumentException e) {
				logger.error("Scelta non valida.");
			}
		}
	}

	// creazione Libro/Rivista
	public static void nuovoLibro() {
		System.out.print("Inserisci codice ISBN: ");
		System.out.println("");
		String ISBN = in.nextLine();
		System.out.print("Inserisci il titolo: ");
		System.out.println("");
		String titolo = in.nextLine();
		System.out.print("Inserisci l'anno di pubblicazione: ");
		System.out.println("");
		String anno = in.nextLine();
		System.out.print("Inserisci il numero di pagine: ");
		System.out.println("");
		int pagine = Integer.parseInt(in.nextLine());
		System.out.print("Inserisci autore: ");
		System.out.println("");
		String autore = in.nextLine();
		System.out.print("Inserisci genere: ");
		System.out.println("");
		String genere = in.nextLine();

		Libro libro = new Libro(ISBN, titolo, anno, pagine, autore, genere);

		try {
			scriviArchivio(libro.getInfo());
			System.out.println("");
			System.out.println("***Libro aggiunto correttamente.***");
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nuovaRivista() {
		System.out.print("Inserisci codice ISBN: ");
		String ISBN = in.nextLine();
		System.out.println("");
		System.out.print("Inserisci il titolo: ");
		String titolo = in.nextLine();
		System.out.println("");
		System.out.print("Inserisci l'anno di pubblicazione: ");
		String anno = in.nextLine();
		System.out.println("");
		System.out.print("Inserisci il numero di pagine: ");
		int pagine = Integer.parseInt(in.nextLine());
		System.out.println("");
		System.out.print("Inserisci la periodicità 'SETTIMANALE' / 'MENSILE' / 'SEMESTRALE': ");
		System.out.println("");
		Periodic periodic = Periodic.valueOf((in.nextLine()).toUpperCase());

		Rivista rivista = new Rivista(ISBN, titolo, anno, pagine, periodic);

		try {
			scriviArchivio(rivista.getInfo());
			System.out.println("");
			System.out.println("***Rivista aggiunta correttamente.***");
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void scriviArchivio(String obj) throws IOException {
		FileUtils.writeStringToFile(fileInfo, obj + System.lineSeparator(), ENCODING, true);
	}
}