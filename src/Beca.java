package src;

public class Beca{

    private AcbEnll<Alumnes_SEC> arbreACB;
    private Queue<Alumnes_SEC> llistaDescendent;
    private final Scanner scanner;

    public Beca(){ //TODO: check if correct
        this.arbreACB = new AcbEnll<>();

        // Insert example students
        try {
            arbreACB.inserir(exempleRosa());
            arbreACB.inserir(exempleEnric());
            arbreACB.inserir(exempleRandom("Random1"));
            arbreACB.inserir(exempleRandom("Random2"));
            arbreACB.inserir(exempleRandom("Random3"));
        } catch (ArbreException e) {
            e.printStackTrace();
        }

        //Inicialitza la llista descendent
        this.llistaDescendent = arbreACB.getDescendentList();
    }

    public static void main (String[] args){

        Beca beca = new Beca();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Afegir un nou alumne.");
            System.out.println("2. Esborrar un alumne a partir del seu nom.");
            System.out.println("3. Mostrar tots els alumnes en ordre descendent.");
            System.out.println("4. Esborrar alumnes sense matrícula d’honor.");
            System.out.println("5. Sortir del programa.");
            System.out.print("Seleccioneu una opció: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    beca.afegirAlumne();
                    break;
                case 2:
                    System.out.print("Introdueix el nom de l'alumne a esborrar: ");
                    String nom = scanner.nextLine();
                    try {
                        beca.esborrarAlumne(nom);
                    } catch (ArbreException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println(beca.toString());
                    break;
                case 4:
                    beca.esborrarAlumnesSenseMatricula();
                    break;
                case 5:
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Si us plau, seleccioneu una opció vàlida.");
            }
        } while (option != 5);

        scanner.close();

    }

    private Alumnes_SEC exempleRosa(){
        Alumnes_SEC rosa = new Alumnes_SEC("Rosa");
        rosa.addAssignatura(new Assignatura("Fonaments", 6, 7.0, false));
        rosa.addAssignatura(new Assignatura("POO", 6, 5.0, false));
        rosa.addAssignatura(new Assignatura("EDA", 4, 9.0, false));
        rosa.addAssignatura(new Assignatura("Avanzada", 4, 5.0, false));
        return rosa;
    }

    private Alumnes_SEC exempleEnric(){
        Alumnes_SEC enric = new Alumnes_SEC("Enric");
        enric.addAssignatura(new Assignatura("Fonaments", 6, 8.0, false));
        enric.addAssignatura(new Assignatura("POO", 6, 6.0, false));
        enric.addAssignatura(new Assignatura("EDA", 4, 9.0, true));
        enric.addAssignatura(new Assignatura("Avansada", 4, 3.0, false));
        return enric;
    }

    private Alumnes_SEC exempleRandom(String nom){
        Alumnes_SEC random = new Alumnes_SEC(nom);
        random.addAssignatura(new Assignatura("Fonaments", 6, Math.random() * 10, false));
        random.addAssignatura(new Assignatura("POO", 6, Math.random() * 10, false));
        random.addAssignatura(new Assignatura("EDA", 4, Math.random() * 10, false));
        random.addAssignatura(new Assignatura("Avanzada", 4, Math.random() * 10, false));
        return random;
    }

    public void esborrarAlumnesSenseMatricula(){
        Queue<Alumnes_SEC> tempQueue = new LinkedList<>();

        while (!finalRecorregut()) {
            Alumnes_SEC alumne = segRecorregut();
            if (alumne != null) {
                boolean hasHonor = false;
                for (Assignatura assignatura : alumne.getAssignatures()) {
                    if (assignatura.isHonor()) {
                        hasHonor = true;
                        break;
                    }
                }
                if (!hasHonor) {
                    try {
                        arbreACB.esborrar(alumne);
                    } catch (ArbreException e) {
                        e.printStackTrace();
                    }
                } else {
                    tempQueue.add(alumne);
                }
            }
        }

        llistaDescendent = tempQueue;
    }

    public void afegirAlumne(){
        System.out.print("Enter the name of the student: ");
        String nom = scanner.nextLine();
        Alumnes_SEC newAlumne = new Alumnes_SEC(nom);

        while (true) {
            System.out.print("Enter the name of the subject (or 'done' to finish): ");
            String subjectName = scanner.nextLine();
            if (subjectName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter the number of credits: ");
            int credits = scanner.nextInt();

            System.out.print("Enter the grade: ");
            double grade = scanner.nextDouble();

            System.out.print("Is it an honor subject? (true/false): ");
            boolean isHonor = scanner.nextBoolean();
            scanner.nextLine(); // Consume the newline character

            Assignatura assignatura = new Assignatura(subjectName, credits, grade, isHonor);
            newAlumne.addAssignatura(assignatura);
        }

        try {
            arbreACB.inserir(newAlumne);
            llistaDescendent = arbreACB.getDescendentList(); // Update the descending list
        } catch (ArbreException e) {
            e.printStackTrace();
        }
    }

    private boolean finalRecorregut(){
        return llistaDescendent.isEmpty();
    }

    private Alumnes_SEC segRecorregut() {
        if (llistaDescendent.isEmpty()) {
            return null;
        }
        return llistaDescendent.poll();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        while (!finalRecorregut()) {
            Alumnes_SEC alumne = segRecorregut();
            if (alumne != null) {
                sb.append(alumne.toString()).append("\n");
            }
        }
        return sb.toString();
    }


}