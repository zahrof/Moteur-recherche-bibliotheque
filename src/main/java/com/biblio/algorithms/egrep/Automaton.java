package com.biblio.algorithms.egrep;

public class Automaton {
    public int id;
    public boolean terminal;

    public Automaton(int id, boolean terminal) {
        this.id = id;
        this.terminal = terminal;
    }

    public Automaton(int id) {
        this.id = id;
    }

    public Automaton() {
        this.id=0;
        this.terminal=false;
    }
}
