package me.nave.util.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a vertex of a graph.
 *
 * @author naveenede
 */
public class Vertex<T>
{
    private final T t;

    /**
     * Adjacency list representation of the edges
     * incident upon this vertex, retains the insertion order.
     */
    private final Set<Vertex<T>> edges = new TreeSet<>();

    /**
     * Any metadata about this vertex is stored in this field.
     * For example, 'pre', 'post' values can be stored here.
     */
    private final Map<Key<?>, Object> metaData = new HashMap<>();

    /**
     * The identifier for this vertex
     */
    private final String identifier;


    ///
    /// Methods
    ///

    public Vertex(T t, String identifier)
    {
        requireNonNull(t, "t must be non-null");
        requireNonNull(identifier, "identifier must be non-null");
        checkArgument(!(identifier = identifier.trim()).isEmpty(), "identifier must be non empty");

        this.t = t;
        this.identifier = identifier;
    }

    public void addEdge(Vertex<T> aVertex)
    {
        requireNonNull(aVertex, "aVertex must be non-null");

        edges.add(aVertex);
    }

   public <S> void putMetaData(Key<S> key, @Nullable S value)
    {
        requireNonNull(key, "key must be non-null");
        this.metaData.put(key, value);
    }

    public <S> S getMetaData(Key<S> key)
    {
        requireNonNull(key, "key must be non-null");
        Object obj = this.metaData.get(key);

        return (obj == null) ? null : key.type.cast(obj);
    }


    ///
    /// Inner Classes
    ///

    @Immutable
    public class Key<S>
    {
        private String keyIdentifier;

        private final Class<S> type;

        public Key(String identifier, Class<S> type)
        {
            this.keyIdentifier = identifier;
            this.type = type;
        }
    }
}
