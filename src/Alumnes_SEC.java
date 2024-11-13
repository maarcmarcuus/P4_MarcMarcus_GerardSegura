package src;

public class Alumnes_SEC implements Comparable<Alumnes_SEC> {

    private Node cap;

    public Alumnes_SEC(String nom) {
        this.cap = new Node(nom);
    }

    public void addAssignatura(Assignatura nova) {
        Node current = cap;
        Node previous = null;
        boolean found = false;

        // Traverse the linked list to find if the assignment already exists
        while (current != null) {
            if (current.info.equals(nova)) {
                current.info = nova; // Overwrite with new information
                found = true;
                break;
            }
            previous = current;
            current = current.next;
        }

        // If the assignment does not exist, add it to the end of the list
        if (!found) {
            if (previous == null) {
                cap.next = new Node(nova);
            } else {
                previous.next = new Node(nova);
            }
        }

        // Recalculate the average grade
        recalculateAverage();
    }

    private void recalculateAverage() {
        Node current = cap;
        double totalPoints = 0;
        int totalCredits = 0;

        while (current != null) {
            totalPoints += current.info.getPunts();
            totalCredits += current.info.getCredits();
            current = current.next;
        }

        double average = totalPoints / totalCredits;
        cap.info.setNota(average);
    }

    public boolean hiHa(int punts) {
        Node current = cap;
        while (current != null) {
            if (current.info.getPunts() == punts) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int compareTo(Alumnes_SEC other) {
        double thisAverage = this.cap.info.getNota();
        double otherAverage = other.cap.info.getNota();

        if (thisAverage < otherAverage) {
            return -1;
        } else if (thisAverage > otherAverage) {
            return 1;
        } else {
            return 0;
        }
    }



    //TODO: Check if this is the correct implementation of the toString method
    public String toString() {
        return "Alumne: " + cap.info.getNom() + " - Nota mitjana: " + cap.info.getNota();
    }

    private class Node {
        Node next;
        Assignatura info;

        public Node(String nom){
            this.info = new Assignatura (nom);
            next = null;
        }

        public Node(Assignatura info) {
            this.info = info;
            next = null;
        }

    }
}
