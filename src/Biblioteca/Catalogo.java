package Biblioteca;

abstract public class Catalogo {
	private String ISBN;
	private String titolo;
	private String anno;
	private int pagine;
	
	public Catalogo(String ISBN, String titolo, String anno, int pagine) {
		super();
		this.ISBN = ISBN;
		this.titolo = titolo;
		this.anno = anno;
		this.pagine = pagine;
	}

	public String getAnno() {
		return anno;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getPagine() {
		return pagine;
	}

	public abstract String getTipo();
}