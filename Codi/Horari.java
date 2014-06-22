package horari;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Horari {

	private EspaiHorari[][] matriu;
	
	// classe per a mapejar l'excel a objecte
	private class ObjecteExcel{
		private String nomAssignatura;
		private int nombreAlumnes;
		private String grups;
		private String tipus;
		
		public ObjecteExcel(String nom, int alumnes, String grups, String tipus){
			this.nomAssignatura = nom;
			this.nombreAlumnes = alumnes;
			this.grups = grups;
			this.tipus = tipus;
		}
		
		public String getNomAssignatura() {
			return nomAssignatura;
		}
		/*public void setNomAssignatura(String nomAssignatura) {
			this.nomAssignatura = nomAssignatura;
		}
		public int getNombreAlumnes() {
			return nombreAlumnes;
		}
		public void setNombreAlumnes(int nombreAlumnes) {
			this.nombreAlumnes = nombreAlumnes;
		}*/
		public String getGrups() {
			return grups;
		}
		/*public void setGrups(String grups) {
			this.grups = grups;
		}*/
		public String getTipus() {
			return tipus;
		}
		/*public void setTipus(String tipus) {
			this.tipus = tipus;
		}*/
	}
	
	/**
	 * Constructora per defecte de la classe horari
	 */
	public Horari()
	{
		matriu = new EspaiHorari[6][5];
		
		for (int fila = 0; fila < 6; ++fila){
			for (int col = 0; col < 5; ++col){
				
				// crear i introduir els espais horaris a cada posicio de la matriu
				
				String dia = columnaDia(col);
				String hora = filaHora(fila);
				
				EspaiHorari espai = new EspaiHorari();
				espai.setDia(dia);
				espai.setHora(hora);
				
				matriu[fila][col] = espai;
			}
		}
	}
	
	/**
	 * Funció que crearà l'horari
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public void creaHorari(String quatri) throws BiffException, IOException
	{
		// llegir excels de cada Grau del quatri indicat i afegir la informacio a un conjunt d'assignatures pendents per afegir a l'horari
		Set<ObjecteExcel> informatica = obtenirInfo(Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Informatica.xls")), quatri);
		Set<ObjecteExcel> electronica = obtenirInfo(Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Electronica.xls")), quatri);
		Set<ObjecteExcel> mecanica = obtenirInfo(Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Mecanica.xls")), quatri);
		Set<ObjecteExcel> electrica = obtenirInfo(Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Electrica.xls")), quatri);
		Set<ObjecteExcel> disseny = obtenirInfo(Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Disseny.xls")), quatri);
		
		// obtenir les aules dels excel
		Workbook aules = Workbook.getWorkbook(new File("/home/rafael/Projecte-PROP/BaseDades/Aules.xls"));
		Sheet fullaAules = aules.getSheet("Hoja1");
		
		Set<String> grupsInformatica = obtenirGrups(informatica);
		Set<String> grupsElectronica = obtenirGrups(electronica);
		Set<String> grupsMecanica = obtenirGrups(mecanica);
		Set<String> grupsElectrica = obtenirGrups(electrica);
		Set<String> grupsDisseny = obtenirGrups(disseny);
		
		for (int fila = 0; fila < 6; ++fila){
			for (int col = 0; col < 5; ++col){

				Set<String> aulesOcupades = new HashSet<String>();
				
				// obtenir l'espai horari
				EspaiHorari espai = matriu[fila][col];
				
				// afegir assignatura a l'espai horari en la posicio "grau"
				for (int grau = 0; grau < 5; ++grau){
					Set<ObjecteExcel> aux;
					Set<String> auxGrups;
					String nomGrau;
					
					switch(grau){
					case 0:
						aux = informatica;
						auxGrups = grupsInformatica;
						nomGrau = "INFORMATICA";
						break;
					case 1:
						aux = electronica;
						auxGrups = grupsElectronica;
						nomGrau = "ELECTRONICA";
						break;
					case 2:
						aux = mecanica;
						auxGrups = grupsMecanica;
						nomGrau = "MECANICA";
						break;
					case 3:
						aux = electrica;
						auxGrups = grupsElectrica;
						nomGrau = "ELECTRICA";
						break;
					case 4:
						aux = disseny;
						auxGrups = grupsDisseny;
						nomGrau = "DISSENY";
						break;
					default:
						aux = informatica;
						auxGrups = grupsInformatica;
						nomGrau = "INFORMATICA";
						break;
					}
					
					for (String codiGrup : auxGrups){
						
						// escollir assignatura i introduir-la si es pot
						boolean introduida = false;
						Iterator<ObjecteExcel> it = aux.iterator();
						while (it.hasNext() && !introduida){
							ObjecteExcel objExcel = it.next();
							
							String nomAssignatura = objExcel.getNomAssignatura();
							String grupAlumnes = objExcel.getGrups();
							String tipus = objExcel.getTipus();
							
							Pair<String, String> aula = obtenirAula(fullaAules, tipus, aulesOcupades);
							if (aula != null){
								aulesOcupades.add(aula.getFirst());
								
								int repes = 0;
								ArrayList<ArrayList<Assignatura>> assignatures = espai.getAssignatures();
								for (int i = 0; i < grau; ++i){
									ArrayList<Assignatura> assignaturesGrau = assignatures.get(i);
									
									for (Assignatura assig : assignaturesGrau){
										if (assig.Nom().equalsIgnoreCase(nomAssignatura)){
											++repes;
										}
									}
								}
								
								// si la assignatura no es repeteix més de dos cops, introduirla i 
								// treurela del set d'assignatures pendents per afegir a l'horari
								if (repes <= 2 && (grupAlumnes.equalsIgnoreCase(codiGrup) || grupAlumnes.startsWith(codiGrup))){
									
									aux.remove(objExcel);
									
									// si el tipus d'assignatura és "teoria", afegir l'assignatura
									if (tipus.equalsIgnoreCase("T")){
										
										Assignatura assignatura = new Assignatura(nomAssignatura, nomGrau, tipus, aula, grupAlumnes);
										
										ArrayList<Assignatura> assignaturesGrau = assignatures.get(grau);
										assignaturesGrau.add(assignatura);
										assignatures.set(grau, assignaturesGrau);
										espai.setAssignatures(assignatures);
										
										introduida = true;
										
									} else {  // si el tipus és "practica", fer dos grups en dues aules diferents
										
										Iterator<ObjecteExcel> it2 = aux.iterator();
										boolean trobat = false;
										while (it2.hasNext() && !trobat){
											ObjecteExcel obj = it2.next();
											
											if (obj.getGrups().startsWith(codiGrup) || obj.getGrups().equalsIgnoreCase(codiGrup)){
												if (obj.getNomAssignatura().equalsIgnoreCase(nomAssignatura) && obj.getTipus().equalsIgnoreCase(tipus)){
													Pair<String, String> novaAula = obtenirAula(fullaAules, tipus, aulesOcupades);
													if (novaAula != null){
														grupAlumnes += "/"+ obj.getGrups();
														
														String auxiliar = aula.getFirst();
														auxiliar += "/"+novaAula.getFirst();
														aula.setFirst(auxiliar);
														
														aux.remove(obj);
														trobat = true;
													}
												}
											}
										}
										
										Assignatura assignatura = new Assignatura(nomAssignatura, nomGrau, tipus, aula, grupAlumnes);
										
										ArrayList<Assignatura> assignaturesGrau = assignatures.get(grau);
										assignaturesGrau.add(assignatura);
										assignatures.set(grau, assignaturesGrau);
										espai.setAssignatures(assignatures);
										
										introduida = true;
									}
								}
							}
						}
					}
				}
				
				matriu[fila][col] = espai;

			}
		}
		
		
		// per cada espai de la matriu, afegir assignatura de cada grau a cada posicio de l'array
		// OJO: si el grup es massa gran, es divideix en dos dies diferents, no en diferents aules el mateix dia
		
		//Posicio de les assignatures a l'array
		//	0: informatica
		//	1: electronica
		//	2: mecanica
		//	3: electrica
		//	4: disseny
	}
	
	private Set<String> obtenirGrups(Set<ObjecteExcel> set)
	{
		Set<String> out = new HashSet<String>();
		
		for (ObjecteExcel obj : set){
			String grup = obj.getGrups();
			
			if (grup.length() == 3){
				out.add(grup);
			} else {
				out.add(grup.substring(0,3));
			}
		}
		
		return out;
	}
	
	/**
	 * Mètode per a mapejar la informació de l'excel a un conjunt de ObjecteExcel
	 * 
	 * @param llibreExcel	Excel a llegir
	 * @param fullaEscollida	Fulla a llegir de l'excel
	 * @return	Conjunt d'assignatures pendents per afegir a l'horari
	 */
	private Set<ObjecteExcel> obtenirInfo(Workbook llibreExcel, String fullaEscollida)
	{
		Set<ObjecteExcel> out = new HashSet<ObjecteExcel>();
		
		Sheet fulla = llibreExcel.getSheet(fullaEscollida);
		
		for (int i = 1; i < fulla.getRows(); ++i){
			String nom = fulla.getCell(0, i).getContents();
			//String num = fulla.getCell(1, i).getContents();
			String[] grups = fulla.getCell(2, i).getContents().split(" ");
			//int grupsDiferents = Integer.valueOf(num) / 15;
			String tipus = fulla.getCell(3, i).getContents();
			
			for (int j = 0 ; j < grups.length; ++j){
				if (tipus.equalsIgnoreCase("P")){
					ObjecteExcel objecte = new ObjecteExcel(nom, 15, grups[j]+"11", tipus);
					out.add(objecte);
					
					objecte = new ObjecteExcel(nom, 15, grups[j]+"12", tipus);
					out.add(objecte);
					
				} else {
					ObjecteExcel objecte = new ObjecteExcel(nom, 30, grups[j], tipus);
					out.add(objecte);
				}
			}
		}
		
		return out;
	}
	
	/**
	 * Mètode per a obtenir una aula adient segons el tipus d'assignatura
	 * 
	 * @param fulla	Excel amb les aules disponibles
	 * @param tipus	Tipus d'aula (practica o teoria) que volem obtenir
	 * @param aulesOcupades	Conjunt d'aules ja ocupades
	 * @return	Pair amb l'aula i el tipus d'aula
	 */
	private Pair<String, String> obtenirAula(Sheet fulla, String tipus, Set<String> aulesOcupades)
	{
		
		boolean found = false;
		
		String nomAula = "";
		String tipusAula = "";
		
		for (int i = 1; i < fulla.getRows() && !found; ++i){
			nomAula = fulla.getCell(0, i).getContents();
			tipusAula = fulla.getCell(2, i).getContents();
			
			if (tipusAula.equalsIgnoreCase(tipus) && !aulesOcupades.contains(nomAula)){
				found = true;
			}
		}
		
		Pair<String, String> out = null;
		if (found){
			out = new Pair<String, String>(nomAula, tipusAula);
		}
		
		return out;
	}
	
	/**
	 * Funcio que retorna la franja horària a la que es refereix el número donat
	 * 
	 * @param n	Número que "convertirem" a hora
	 * @return	Franja horària a la que correspon el número
	 */
	private String filaHora(int n)
	{
		String out;
		
		switch(n){
		case 0:
			out = "8:30-10:30";
			break;
		case 1:
			out = "10:30-12:30";
			break;
		case 2:
			out = "12:30-14:30";
			break;
		case 3:
			out = "15:00-17:00";
			break;
		case 4:
			out = "17:00-19:00";
			break;
		case 5:
			out = "19:00-21:00";
			break;
		default:
			out = "";
			break;
		}
		
		return out;
	}
	
	/**
	 * Funció que retorna el dia corresponent al número donat
	 * 
	 * @param n	Número que "convertirem" a dia
	 * @return	Dia corresponent al número donat
	 */
	private String columnaDia(int n)
	{
		String out;
		
		switch(n){
		case 0:
			out = "Dilluns";
			break;
		case 1:
			out = "Dimarts";
			break;
		case 2:
			out = "Dimecres";
			break;
		case 3:
			out = "Dijous";
			break;
		case 4:
			out = "Divendres";
			break;
		default:
			out = "";
			break;
		}
		
		return out;
	}
	
	/**
	 * Funció que, donat un número, retorna el nom del grau
	 * @param n	Número que es passa
	 * @return	Nom del grau que correspon al numero
	 */
	private String nomCodiGrau(int n)
	{
		String out;
		
		switch(n){
		case 0:
			out = "INFORMATICA";
			break;
		case 1:
			out = "ELECTRONICA";
			break;
		case 2:
			out = "MECANICA";
			break;
		case 3:
			out = "ELECTRICA";
			break;
		case 4:
			out = "DISSENY";
			break;
		default:
			out = "INFORMATICA";
			break;
		}
		
		return out;
	}
	
	/**
	 * Funció que mostrarà l'horari creat per al grau indicat
	 * 
	 * @param grauN	Grau a mostrar
	 */
	public void mostraHorari(int grauN)
	{
		System.out.println(nomCodiGrau(grauN));
		
		String espaiBlanc = "";
		while (espaiBlanc.length() < 25) espaiBlanc += " ";
		espaiBlanc = "| "+espaiBlanc+" |";
		
		String separador = "";
		for (int i = 0; i < 5; ++i){
			separador += espaiBlanc;
		}
		
		String vora = "";
		for (int col = 0; col < 5; ++col){
			vora += "+---------------------------+";
		}
		
		for (int fila = 0; fila < 6; ++fila){
			
			int midaMax = 0;
			for (int col = 0; col < 5; ++col){

				EspaiHorari espai = matriu[fila][col];

				ArrayList<ArrayList<Assignatura>> assignatures = espai.getAssignatures();
				ArrayList<Assignatura> assignaturesGrau = assignatures.get(grauN);
				if (midaMax < assignaturesGrau.size()){
					midaMax = assignaturesGrau.size();
				}
			}
			
			if (midaMax == 0){
				System.out.println(vora);
				for (int i = 0; i < 5; ++i){
					System.out.println(separador);
				}
				System.out.println(vora);
				
			} else {
				int auxCount = 0;
				
				System.out.println(vora);
				
				while (auxCount < midaMax){
					
					String info = "";
					String assignatura = "";
					String grup = "";
					//String grau = "";
					String tipus = "";
					String aula = "";
					
					for (int col = 0; col < 5; ++col){
						
						EspaiHorari espai = matriu[fila][col];
						
						ArrayList<ArrayList<Assignatura>> assignatures = espai.getAssignatures();
						ArrayList<Assignatura> assignaturesGrau = assignatures.get(grauN);
						
						if (assignaturesGrau.size() > auxCount){
							Assignatura assig = assignaturesGrau.get(auxCount);
							
							// mostra la info de l'espai horari que hi hagi
							String aux = espai.getDia() +"  " + espai.getHora();
							while (aux.length() < 25) aux += " ";
							info += "| "+ aux +" |";

							aux = "Assignatura: "+assig.Nom();
							while (aux.length() < 25) aux += " ";
							assignatura += "| "+ aux +" |";

							aux = "Grup: "+assig.Grup();
							while (aux.length() < 25) aux += " ";
							grup += "| "+aux +" |";

							/*aux = "Grau: "+assig.Grau();
							while (aux.length() < 25) aux += " ";
							grau += "| "+aux + " |";*/

							aux = "Tipus: "+assig.Tipus();
							while (aux.length() < 25) aux += " ";
							tipus += "| "+aux+" |";

							aux = "Aula: "+assig.Aula().getFirst();
							while (aux.length() < 25) aux += " ";
							aula += "| "+aux+" |";
							
						} else {
							info += espaiBlanc;
							assignatura += espaiBlanc;
							grup += espaiBlanc;
							//grau += espaiBlanc;
							tipus += espaiBlanc;
							aula += espaiBlanc;
						}
					}
					
					System.out.println(info);
					System.out.println(assignatura);
					System.out.println(grup);
					//System.out.println(grau);
					System.out.println(tipus);
					System.out.println(aula);
					if (auxCount != midaMax-1){
						System.out.println(separador);
					}
					
					++auxCount;
				}
				System.out.println(vora);
			}
		}
	}
}

