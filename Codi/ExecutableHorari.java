package horari;

import java.io.IOException;
import java.util.Scanner;

import jxl.read.biff.BiffException;

public class ExecutableHorari {

	public static void main(String[] args) throws BiffException, IOException {
		
		System.out.println("GENERADOR D'HORARIS\n");
		
		Scanner scan = new Scanner(System.in);
		int opcioA = 0;
		while (opcioA <= 0 || opcioA >= 6){
			menuGraus();
			opcioA = scan.nextInt();
			System.out.println();
		}
		
		int opcioB = 0;
		while (opcioB <= 0 || opcioB >= 9){
			menuQuatri();
			opcioB = scan.nextInt();
			System.out.println();
		}
		scan.close();
		
		Horari horari = new Horari();
		horari.creaHorari(stringQuatri(opcioB));
		
		horari.mostraHorari(opcioA-1, stringQuatri(opcioB));
		
		System.out.println("(All rights reserved: Andrea, Xavi, Laura, Rafa)");
	}

	
	private static void menuGraus()
	{
		System.out.println("------GRAUS------");
		System.out.println("  1. INFORMÀTICA");
		System.out.println("  2. ELECTRÒNICA");
		System.out.println("  3. MECÀNICA");
		System.out.println("  4. ELÈCTRICA");
		System.out.println("  5. DISSENY");
		System.out.print("Tria un grau: ");
	}
	
	private static void menuQuatri()
	{
		System.out.println("------QUATRIMESTRE------");
		System.out.println("  1. Q1");
		System.out.println("  2. Q2");
		System.out.println("  3. Q3");
		System.out.println("  4. Q4");
		System.out.println("  5. Q5");
		System.out.println("  6. Q6");
		System.out.println("  7. Q7");
		System.out.println("  8. Q8");
		System.out.print("Tria un quatrimestre: ");
	}
	
	private static String stringQuatri(int n)
	{
		String out;
		
		switch(n){
		case 1:
			out = "Q1";
			break;
		case 2:
			out = "Q2";
			break;
		case 3:
			out = "Q3";
			break;
		case 4:
			out = "Q4";
			break;
		case 5:
			out = "Q5";
			break;
		case 6:
			out = "Q6";
			break;
		case 7:
			out = "Q7";
			break;
		case 8:
			out = "Q8";
			break;
		default:
			out = "Q1";
			break;
		}
		
		return out;
	}
}
