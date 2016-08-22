package me.nave.util.graph;

/**
 * @author naveenede
 */
public class Graphs
{
    public void dfs()
    {
        Vertex<String> a = new Vertex<>("a", "a");
        Vertex<String> b = new Vertex<>("b", "b");
        Vertex<String> c = new Vertex<>("c", "c");
        Vertex<String> d = new Vertex<>("d", "d");

        a.addEdge(b);
        b.addEdge(c);
        c.addEdge(d);
        d.addEdge(a);
    }
}
