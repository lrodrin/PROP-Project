package assignatura;


public class Assignatura {
	private String Nom;
	private String Grau;
	private String Tipus;
	private Pair(Nom,Tipus) Aula;
	private String Grup;
		
	 /**
	 * Constructora per defecte de la classe assignatura
	 */
	public Assignatura (String nom, String grau, Pair(nom,tipus) aula, String grup) {
		Nom = nom;
		Grau = grau;
		Aula = aula;
		Grup = grup;
	}
	
	/**
	 * Funció que retornarà el Nom de l'assignatura
	 */
	public String Nom() {
		return Nom;
	}
	
	/**
	 * Funció que retornarà el Grau de l'assignatura
	 */
	public String Grau() {
		return Grau;
	}
	
	/**
	 * Funció que retornarà el Aula de l'assignatura
	 */
	public Pair Aula() {
		return Aula;
	}
	
	/**
	 * Funció que retornarà el Grup de l'assignatura
	 */
	public String Grup() {
		return Grup;
	}
	
	/**
	 * Funció que retornarà el l'assignatura en String
	 */
	public String Assignatura() {
		syste.out.printf("Assignatura %s, del grau %s, de l'aula %s i del grup %s", Nom, Grau, Aula.nom, Grup);
	}
}
