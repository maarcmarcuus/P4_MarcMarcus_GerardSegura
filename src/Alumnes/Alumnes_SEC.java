package Alumnes;

public class Alumnes_SEC implements Comparable<Alumnes_SEC> {

    private final Node cap;

    public Alumnes_SEC(String nom) {
        this.cap = new Node(nom);
    }

    public void addAssignatura(Assignatura nova) {
        Node current = cap;
        Node previous = null;
        boolean found = false;

        while (current != null) {
            if (current.info.equals(nova)) {
                current.info = nova;
                found = true;
                break;
            }
            previous = current;
            current = current.next;
        }

        if (!found) {
            if (previous == null) {
                cap.next = new Node(nova);
            } else {
                previous.next = new Node(nova);
            }
        }

        recalculateAverage();
    }

    private void recalculateAverage() {
        Node current = cap.next;
        int totalCredits = 0;
        double divisor = 0;

        while (current != null) {
            divisor += (double) current.info.getCredits() * current.info.getPunts();
            totalCredits += current.info.getCredits();
            current = current.next;
        }

        double average = totalCredits == 0 ? 0 : divisor / totalCredits;
        cap.info.setNota(average);
    }

    public boolean hiHa(int punts) {
        Node current = cap.next;
        while (current != null) {
            if (current.info.getPunts() == punts) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int compareTo(Alumnes_SEC other) {
        double thisAverage = this.cap.info.getNota();
        double otherAverage = other.cap.info.getNota();

        return Double.compare(thisAverage, otherAverage);
    }

    @Override
    public String toString() {
        return "Alumne: " + cap.info.getNom() + " - Nota mitjana: " + cap.info.getNota();
    }

    public Assignatura[] getAssignatures() {
        Node current = cap.next;
        Assignatura[] assignatures = new Assignatura[100];
        int i = 0;

        while (current != null) {
            assignatures[i++] = current.info;
            current = current.next;
        }

        return assignatures;
    }

    public Object getNom() {
        return cap.info.getNom();
    }

    private static class Node {
        Node next;
        Assignatura info;

        public Node(String nom) {
            this.info = new Assignatura(nom);
            this.next = null;
        }

        public Node(Assignatura info) {
            this.info = info;
            this.next = null;
        }
    }
}