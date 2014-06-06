public class EspaiHorari {
    private String dia, hora;
    private Assignatura[] assignatura;
  
    public EspaiHorari() {
        this.assignatura = new Assignatura[5];
        for(int i = 0; i < 5; i++) assignatura[i] = null;
    }
    
    public void setDia(String dia) {
        this.dia = dia;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setAssignatura(Assignatura[] assignatura) {
        this.assignatura = assignatura;
    }
    
    public String getDia() {
        return dia;
    }
    
    public String getHora() {
        return hora;
    }
    
    public Assignatura getAssignatura(int i) {
        return assignatura[i];
    }
}
