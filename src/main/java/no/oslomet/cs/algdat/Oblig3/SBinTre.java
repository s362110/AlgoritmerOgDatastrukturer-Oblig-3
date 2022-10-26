package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        // Kommentar

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    // oppgave 2
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    // Oppgave 2
    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    // Oppgave 2
    public boolean tom() {
        return antall == 0;
    }

    // Oppgave 1
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 2
    public int antall(T verdi) {
        // Koden er hentet fra kompendiet.
        Node<T> p = rot;

        int antallVerdi = 0;

        while (p != null){
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0){
                p = p.venstre;
            } else {
                if (cmp == 0){
                    antallVerdi++;
                    p = p.høyre;
                }
            }
        }
        return antallVerdi;

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {
        // Programkode 5.1.7 h) + endringer
        if (p == null){
            throw new NoSuchElementException("Treet er tomt!");
        }

        // Node<T> p = rot;

        while (true){
            if (p.venstre != null){
                p = p.venstre;
            } else if (p.høyre != null){
                p = p.høyre;
            } else {
                return p;
            }
        }

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 3
    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p == null){
            throw new NoSuchElementException("Treet er tomt!");
        }

        if (p.forelder == null){
            p = null;
        } else if (p.forelder.høyre == p) {
            p = p.forelder;
        } else if (p.forelder.venstre == p) {
            if (p.forelder.høyre == null){
                p = p.forelder;
            } else {
                p = førstePostorden(p.forelder.høyre);
            }
        }

        return p;

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 4
    public void postorden(Oppgave<? super T> oppgave) {
        if (rot == null){
            return; // tomt tre
        }

        Node<T> q = førstePostorden(rot);

        oppgave.utførOppgave(q.verdi);

        Node<T> r = nestePostorden(q);

        while (r != null){
            oppgave.utførOppgave(r.verdi);
            r = nestePostorden(r);
        }

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    // Oppgave 4
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null){
            return;
        }

        if (p.høyre != null){
            postordenRecursive(p.høyre, oppgave);
        }

        if (p.venstre != null){
            postordenRecursive(p.venstre, oppgave);
        }

        oppgave.utførOppgave(p.verdi);

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 5
    public ArrayList<T> serialize() {
        ArrayList<T> list = new ArrayList<>();
        ArrayDeque<Node> kø = new ArrayDeque<>();

        kø.addLast(rot);
        while (!kø.isEmpty()){
            Node<T> current = kø.removeFirst();

            if (current.høyre != null){
                kø.addLast(current.høyre);
            }

            if (current.venstre != null){
                kø.addLast(current.venstre);
            }

            list.add(current.verdi);
        }

        return list;
        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    // Oppgave 5
    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tre2 = new SBinTre<>(c);

        for (int i = 0; i < data.size(); ++i){
            tre2.leggInn(data.get(i));
        }
        return tre2;

        // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
