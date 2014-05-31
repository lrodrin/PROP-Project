package horari;

public class Horari {

	private EspaiHorari[][] matriu;
	
	/**
	 * Constructora per defecte de la classe horari
	 */
	public Horari()
	{
		
		matriu = new EspaiHorari[6][5];
		
		for (int fila = 0; fila < 6; ++fila){
			for (int col = 0; col < 5; ++col){
				
				String dia = columnaDia(col);
				String hora = filaHora(fila);
				
				// omplir espais horaris
				
				matriu[fila][col] = null;
			}
		}
		
	}
	
	/**
	 * Funció que crearà l'horari
	 */
	public void creaHorari(){
		
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
	 * Funció que mostrarà l'horari creat
	 */
	public void mostraHorari()
	{
		for (int fila = 0; fila < 6; ++fila){
			for (int col = 0; col < 5; ++col){
				
				// mostra la info de l'espai horari que hi hagi
				
			}
		}
	}
}
