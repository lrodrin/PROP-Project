public class EspaiHorari {
    private String dia, hora;
    private Assignatura[] assignatures;
  
    public EspaiHorari() {
        this.assignatures = new Assignatura[5];
        for(int i = 0; i < 5; i++) assignatures[i] = null;
    }
    
    public void setDia(String dia) {
        this.dia = dia;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setAssignatures(Assignatura[] assignatures) {
        this.assignatures = assignatures;
    }
    
    public String getDia() {
        return dia;
    }
    
    public String getHora() {
        return hora;
    }
    
    public Assignatura getAssignatura(int i) {
        return assignatures[i];
    }

    public Assignatura[] getAssignatures() {
        return assignatures;
    }
}

