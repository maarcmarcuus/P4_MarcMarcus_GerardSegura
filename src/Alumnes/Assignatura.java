package Alumnes;

public class Assignatura {

    private final boolean mHonor;
    private final int credits;
    private final String nom;
    private final int EXCELENT = 9;
    private final int NOTABLE = 7;
    private final int APROVAT = 5;
    private double nota;

    public Assignatura(String nom, int credits, double nota, boolean mHonor) {
        if (credits < 0 || nota < 0) {
            throw new IllegalArgumentException("Nota o crèdits no vàlids");
        }
        this.nota = nota;
        this.credits = credits;
        this.nom = nom;
        this.mHonor = mHonor;
    }

    public Assignatura(String nom) {
        this.nom = nom;
        this.nota = 0.0;
        this.credits = 0;
        this.mHonor = false;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) throws IllegalArgumentException {
        if (nota < 0) {
            throw new IllegalArgumentException("Nota no vàlida");
        }
        this.nota = nota;
    }

    public int getPunts() {
        return switch (this.nota >= EXCELENT ? 3 : this.nota >= NOTABLE ? 2 : this.nota >= APROVAT ? 1 : 0) {
            case 3 -> this.credits * 3;
            case 2 -> this.credits * 2;
            case 1 -> this.credits;
            default -> this.mHonor ? this.credits * 4 : 0;
        };
    }

    public int getCredits() {
        return this.credits;
    }

    @Override
    public String toString() {
        return this.nom + " - Nota: " + this.nota;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Assignatura other) {
            return this.nom.equals(other.nom);
        }
        return false;
    }

    public String getNom() {
        return this.nom;
    }

    public boolean isHonor() {
        return this.mHonor;
    }
}
