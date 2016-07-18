package me.nave.util.graph;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * @author naveenede
 */
public class Graph<T>
{
    private final Set<Vertex<T>> vertices = new HashSet<>();

    public void addVertex(Vertex<T> v)
    {
        requireNonNull(v, "v must be non-null");

        // vertex overrides are ok for now
        vertices.add(v);
    }

    public void addEdge(Vertex<T> u, Vertex<T> v)
    {
        checkArgument(vertices.contains(u), "u is not a vertex in the current graph");
        checkArgument(vertices.contains(v), "v is not a vertex in the current graph");


    }
}
