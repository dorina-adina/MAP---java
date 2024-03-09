import java.util.Objects;

public class Pacient extends Entity {
    private String nume;
    private String prenume;
    private int varsta;

    public Pacient(int ID, String nume, String prenume, int varsta) {
        super(ID);
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Pacient{ ID = " + id + ", nume = " + nume + ", prenume = " + prenume + ", varsta = " + varsta + " }";
//        return id + "," + nume + "," + prenume + "," + varsta + ",";
    }

    public String getNume() {return nume; }
    public String getPrenume() {return prenume; }
    public int getVarsta() {return varsta; }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pacient pacient = (Pacient) obj;
        return id == pacient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
