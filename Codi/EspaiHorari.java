package horari;

import java.util.ArrayList;

public class EspaiHorari {
    private String dia, hora;
    private ArrayList<ArrayList<Assignatura>> assignatures;
  
    public EspaiHorari() {
        this.assignatures = new ArrayList<ArrayList<Assignatura>>();
        for(int i = 0; i < 5; i++){
        	ArrayList<Assignatura> array = new ArrayList<Assignatura>();
        	assignatures.add(array);
        }
    }
    
    public void setDia(String dia) {
        this.dia = dia;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setAssignatures(ArrayList<ArrayList<Assignatura>> assignatures) {
        this.assignatures = assignatures;
    }
    
    public String getDia() {
        return dia;
    }
    
    public String getHora() {
        return hora;
    }
    
    public ArrayList<Assignatura> getGrau(int i) {
        return assignatures.get(i);
    }

    public ArrayList<ArrayList<Assignatura>> getAssignatures() {
        return assignatures;
    }
}

