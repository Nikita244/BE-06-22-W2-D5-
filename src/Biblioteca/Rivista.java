package Biblioteca;

class Rivista extends Catalogo {
    private Periodic periodic;

    public Rivista(String ISBN, String titolo, String anno, int num, Periodic periodic) {
        super(ISBN, titolo, anno, num);
        this.periodic = periodic;
    }

    public Periodic getPeriodic() {
        return periodic;
    }

    public void setPeriodic(Periodic periodic) {
		this.periodic = periodic;
	}

	@Override
    public String getTipo() {
        return "Rivista";
    }
	
    public String getInfo() {
    	return "Tipologia: " + getTipo() + " *** Codice ISBN: " + getISBN() + " *** Titolo: " + getTitolo() + " *** Anno: " + getAnno() + " *** Pagine: " + getPagine()
                + " *** Periodicità : " + getPeriodic();
    }
}