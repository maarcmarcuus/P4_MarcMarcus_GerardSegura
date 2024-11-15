package src;

public class AcbEnll <E extends Comparable<E>> implements Acb<E> {

    private NodeA arrel;



    public AcbEnll(NodeA arrel){
        this.arrel = arrel;
    }

    public AcbEnll(){
        this(null);
    }

    public E arrel() throws ArbreException{
        if(this.arrel = null){
            throw new ArbreException("L'arbre és buit");
        }
        return this.arrel.inf;
    }

    public Acb<E> fillEsquerre(){
        if (arrel == null || arrel.esq == null) {
            return new AcbEnll<>();
        }
        return new AcbEnll<>((NodeA) arrel.esq.clone());
    }

    public Acb<E> fillDret(){
        public Acb<E> fillDret() throws CloneNotSupportedException {
            if (arrel == null || arrel.dret == null) {
                return new AcbEnll<>();
            }
            return new AcbEnll<>((NodeA) arrel.dret.clone());
        }
    }

    public boolean arbreBuit(){
        return arrel == null;
    }

    public void buidar(){
        this.arrel = null;
    }

    public void inserir(E element) throws ArbreException{
        if (arrel == null) {
            arrel = new NodeA(element);
        } else {
            arrel.inserirRecursive(element);
        }
    }

    public void esborrar(E element) throws ArbreException{
        if (arrel == null) {
            throw new ArbreException("L'arbre és buit");
        }
        arrel = arrel.esborrarRecursive(element);
    }

    public boolean membre(E element){
        return arrel != null && arrel.membreRecursive(element);
    }

    public Queue<E> getAscendentList(){
        Queue<E> queue = new LinkedList<>();
        if (arrel != null) {
            arrel.omplirInordre(queue);
        }
        return queue;
    }

    public Queue<E> getDescendentList(){
        Queue<E> queue = new LinkedList<>();
        if (arrel != null) {
            arrel.omplirReverseInordre(queue);
        }
        return queue;
    }

    public int cardinalitat(){
        if (arrel == null) {
            return 0;
        }
        return arrel.cardinalitatRecursive();
    }



    private class NodeA implements Cloneable {

        private E inf;

        private NodeA dret;
        private nodeA esq;


        public NodeA (NodeA dret, NodeA esq, E inf){
            this.dret = dret;
            this.esq = esq;
            this.inf = inf;
        }

        public NodeA (E inf){
            this(null, null, inf); //TODO: Check if this is correct
        }

        private boolean membreRecursive(E element){
            if (element.compareTo(inf) == 0) {
                return true;
            } else if (element.compareTo(inf) < 0) {
                return esq != null && esq.membreRecursive(element);
            } else {
                return dret != null && dret.membreRecursive(element);
            }
        }

        private void inserirRecursive(E element){
            if (element.compareTo(inf) < 0) {
                if (esq == null) {
                    esq = new NodeA(element);
                } else {
                    esq.inserirRecursive(element);
                }
            } else if (element.compareTo(inf) > 0) {
                if (dret == null) {
                    dret = new NodeA(element);
                } else {
                    dret.inserirRecursive(element);
                }
            } else {
                throw new IllegalArgumentException("Element ja existeix");
            }
        }

        private E esborrarMinim(NodeA node){
            if (node.esq == null) {
                return node.inf;
            } else {
                return esborrarMinim(node.esq);
            }
        }

        private NodeA esborrarRecursive(E element){
            if (element.compareTo(inf) < 0) {
                if (esq != null) {
                    esq = esq.esborrarRecursive(element);
                }
            } else if (element.compareTo(inf) > 0) {
                if (dret != null) {
                    dret = dret.esborrarRecursive(element);
                }
            } else {
                if (esq == null) {
                    return dret;
                } else if (dret == null) {
                    return esq;
                } else {
                    inf = esborrarMinim(dret);
                    dret = dret.esborrarRecursive(inf);
                }
            }
            return this;
        }

        private void omplirInordre (Queue<E> cua){
            if (esq != null) {
                esq.omplirInordre(cua);
            }
            cua.add(inf);
            if (dret != null) {
                dret.omplirInordre(cua);
            }
        }

        private void omplirReverseInordre (Queue<E> cua){
            if (dret != null) {
                dret.omplirReverseInordre(cua);
            }
            cua.add(inf);
            if (esq != null) {
                esq.omplirReverseInordre(cua);
            }
        }

        private int cardinalitatRecursive(){
            int count = 1; // Count the current node
            if (esq != null) {
                count += esq.cardinalitatRecursive();
            }
            if (dret != null) {
                count += dret.cardinalitatRecursive();
            }
            return count;
        }

        public Object clone(){
            NodeA cloned = (NodeA) super.clone();
            if (esq != null) {
                cloned.esq = (NodeA) esq.clone();
            }
            if (dret != null) {
                cloned.dret = (NodeA) dret.clone();
            }
            return cloned;
        }



    }

}