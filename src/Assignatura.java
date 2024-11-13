package src;

public class Assignatura {

    private final boolean mHonor;
    private double nota;
    private final int credits;
    private final String nom;

    private final int EXCELENT = 9;
    private final int NOTABLE = 7;
    private final int APROVAT = 5;

    public Assignatura (String nom, int credits, double nota, boolean mHonor){
        this.nota = nota;
        this.credits = credits;
        this.nom = nom;
        this.mHonor = mHonor;
    }

    public Assignatura (String nom){
        this.nom = nom;
        this.nota = 0.0;
        this.credits = 0;
        this.mHonor = false;
    }

    public void setNota(double nota) throws IllegalArgumentException{
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota no vàlida");
        }
        this.nota = nota;
    }

    public double getNota(){
        return this.nota;
    }

    public int getPunts(){
        if(this.mHonor){
            return this.credits * 4;
        } else if (this.nota >= EXCELENT){
            return this.credits * 3;
        } else if (this.nota >= NOTABLE){
            return this.credits * 2;
        } else if (this.nota >= APROVAT){
            return this.credits;
        } else {
            return 0;
        }
    }

    public int getCredits(){
        return this.credits;
    }

    public String toString(){
        return this.nom + " (" + this.credits + " crèdits) - Nota: " + this.nota ;
    }

    public boolean equals (Assignatura assig2){
        return assig2.nom == this.nom;
    }

    public String getNom() {
        return this.nom;
    }
}
