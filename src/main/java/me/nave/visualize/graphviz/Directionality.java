package me.nave.visualize.graphviz;

/**
 * @author naveenede
 */
enum Directionality {
    TB, LR;

    @Override
    public String toString() {
        return String.format("rankdir = %s;", this.name());
    }
}
