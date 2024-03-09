package domain;

import java.util.Comparator;
import java.util.List;

public class Reteta implements Comparator<Reteta> {
    private String nume;
    private int timpGatire;
    private List<String> ingrediente;

    public Reteta(String nume, int timpGatire, List<String> ingrediente) {
        this.nume = nume;
        this.timpGatire = timpGatire;
        this.ingrediente = ingrediente;
    }

    public String getNume() {
        return nume;
    }

    public int getTimpGatire() {
        return timpGatire;
    }

    public List<String> getIngrediente() {
        return ingrediente;
    }

    @Override
    public String toString() {
        return "Reteta{" +
                "nume='" + nume + '\'' +
                ", timpGatire=" + timpGatire +
                ", ingrediente=" + ingrediente +
                '}';
    }

    @Override
    public int compare(Reteta r1, Reteta r2) {
        int comparatieIngrediente = Integer.compare(r1.getIngrediente().size(), r2.getIngrediente().size());
        if (comparatieIngrediente != 0) {
            return comparatieIngrediente;
        }
        return r1.getNume().compareTo(r2.getNume());
    }
}
