package horari;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class Test {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		
		Horari horari = new Horari();
		horari.creaHorari("Q2");
		
		for (int i = 0; i < 5; ++i){
			horari.mostraHorari(i);
			System.out.println();
		}
	}

}
