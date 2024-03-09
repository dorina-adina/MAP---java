package domain;


public class Programare extends Entity {

    private Pacient pacient;
    private String data;
    private String ora;
    private String scopulProgramarii;

    public Programare(int ID, Pacient pacient, String data, String ora, String scopulProgramarii) {
        super(ID);
        this.pacient = pacient;
        this.data = data;
        this.ora = ora;
        this.scopulProgramarii = scopulProgramarii;
    }

    @Override
    public String toString() {
        return "Programare{ "
                + "ID = " + id + ", " + "pacient: " + pacient + ", " + "data: " + data + ", " + "ora : " + ora + ", " + "scopul programarii: " + scopulProgramarii + "}";
    }
    public Pacient getPacient() {return pacient; }
    public String getData() {return data; }
    public String getOra() {return ora;}
    public String getScopulProgramarii() {return scopulProgramarii; }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setOra(String ora) {
        this.ora = ora;
    }
    public void setScopulProgramarii(String scopulProgramarii) {
        this.scopulProgramarii = scopulProgramarii;
    }



}
