package assignatura;


public class Assignatura {
	private String Nom;
	private String Grau;
	private String Tipus;
	private Pair<String, String> Aula;
	private String Grup;
		
	 /**
	 * Constructora per defecte de la classe assignatura
	 */
	public Assignatura (String nom, String grau, Pair<String, String> aula, String grup) {
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
		String out = "Assignatura "+Nom+", del grau "+Grau+", de l'aula "+Aula.getFirst()+" i del grup "+Grup;
		return out;
	}
}
