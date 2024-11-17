package EstructuraArbre;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E> {
    private NodeA arrel;

    public AcbEnll(NodeA<E> arrel) {
        this.arrel = arrel;
    }

    public AcbEnll() {
        this.arrel = null;
    }

    @Override
    public void inserir(E element) throws ArbreException {
        arrel = inserirRecursive(arrel, element);
    }

    private NodeA<E> inserirRecursive(NodeA<E> node, E element) throws ArbreException {
        if (node == null) {
            return new NodeA<>(element);
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.esquerre = inserirRecursive(node.esquerre, element);
        } else if (cmp > 0) {
            node.dret = inserirRecursive(node.dret, element);
        } else {
            throw new ArbreException("Element already exists");
        }
        return node;
    }

    @Override
    public void esborrar(E element) throws ArbreException {
        arrel = esborrarRecursive(arrel, element);
    }

    private NodeA<E> esborrarRecursive(NodeA<E> node, E element) throws ArbreException {
        if (node == null) {
            throw new ArbreException("Element not found");
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.esquerre = esborrarRecursive(node.esquerre, element);
        } else if (cmp > 0) {
            node.dret = esborrarRecursive(node.dret, element);
        } else {
            if (node.esquerre == null) {
                return node.dret;
            } else if (node.dret == null) {
                return node.esquerre;
            }
            NodeA<E> minNode = esborrarMinim(node.dret);
            node.element = minNode.element;
            node.dret = esborrarRecursive(node.dret, minNode.element);
        }
        return node;
    }

    private NodeA<E> esborrarMinim(NodeA<E> node) {
        while (node.esquerre != null) {
            node = node.esquerre;
        }
        return node;
    }

    @Override
    public boolean membre(E element) {
        return membreRecursive(arrel, element);
    }

    private boolean membreRecursive(NodeA<E> node, E element) {
        if (node == null) {
            return false;
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            return membreRecursive(node.esquerre, element);
        } else if (cmp > 0) {
            return membreRecursive(node.dret, element);
        } else {
            return true;
        }
    }

    @Override
    public E arrel() throws ArbreException {
        if (arrel == null) {
            throw new ArbreException("Tree is empty");
        }
        return (E)arrel.element;
    }

    @Override
    public Acb<E> fillEsquerre() throws CloneNotSupportedException {
        if (arrel == null || arrel.esquerre == null) {
            return new AcbEnll<>();
        }
        return new AcbEnll<>(arrel.esquerre.clone());
    }

    @Override
    public Acb<E> fillDret() throws CloneNotSupportedException {
        if (arrel == null || arrel.dret == null) {
            return new AcbEnll<>();
        }
        return new AcbEnll<>(arrel.dret.clone());
    }

    @Override
    public boolean arbreBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        arrel = null;
    }

    public Queue<E> getAscendentList() {
        Queue<E> queue = new LinkedList<>();
        omplirInOrdre(arrel, queue);
        return queue;
    }

    private void omplirInOrdre(NodeA<E> node, Queue<E> queue) {
        if (node != null) {
            omplirInOrdre(node.esquerre, queue);
            queue.add(node.element);
            omplirInOrdre(node.dret, queue);
        }
    }

    public Queue<E> getDescendentList() {
        Queue<E> queue = new LinkedList<>();
        omplirReverseInOrdre(arrel, queue);
        return queue;
    }

    private void omplirReverseInOrdre(NodeA<E> node, Queue<E> queue) {
        if (node != null) {
            omplirReverseInOrdre(node.dret, queue);
            queue.add(node.element);
            omplirReverseInOrdre(node.esquerre, queue);
        }
    }

    public int cardinalitat() {
        return cardinalitatRecursive(arrel);
    }

    private int cardinalitatRecursive(NodeA<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + cardinalitatRecursive(node.esquerre) + cardinalitatRecursive(node.dret);
    }

    public static class NodeA<E extends Comparable<E>> implements Cloneable {
        E element;
        NodeA<E> esquerre, dret;

        NodeA(E element) {
            this.element = element;
            this.esquerre = null;
            this.dret = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected NodeA<E> clone() throws CloneNotSupportedException {
            NodeA<E> cloned = (NodeA<E>) super.clone();
            if (this.esquerre != null) {
                cloned.esquerre = this.esquerre.clone();
            }
            if (this.dret != null) {
                cloned.dret = this.dret.clone();
            }
            return cloned;
        }
    }
}