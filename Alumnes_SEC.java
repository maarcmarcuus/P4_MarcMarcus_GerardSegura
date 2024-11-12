public class Alumnes_SEC implements Comparable<Alumnes_SEC> {

    private Node cap;

    public Alumnes_SEC(String nom) {

    }

    public void addAssignatura(Assignatura nova) {

    }

    public boolean hiHa(int punts) {

    }

    public int compareTo(Alumnes_SEC other) {

    }

    public String toString() {

    }

    private class Node {
        Node next;
        Assignatura info;

        public Node (String nom){
            this.info = new Assignatura (nom);
            next = null;
        }

        public Node(Assignatura info) {
            this.info = info;
            next = null;
        }

    }
}
