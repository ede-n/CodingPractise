package me.nave.visualize.graphviz;

/**
 * @author naveenede
 */
public class GraphVizGraph {

    private final Type graphType;
    private final String graphName;
    private final Directionality directionality;

    private GraphVizGraph(Builder builder)
    {
        this.graphType = builder.type;
        this.graphName = builder.name;
        this.directionality = builder.directionality;
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();

        sb.append(graphType.name().toLowerCase()).append(" ").append(graphName);
        sb.append(" {");
        sb.append("\n");

        sb.append("  " + directionality.toString());

        sb.append("\n");
        sb.append("}");

        return sb.toString();
    }


    class Builder {
        private String name;
        private Type type;
        private Directionality directionality;

        Builder type(Type type) {
            this.type = type;
            return this;
        }

        Builder name(String graphName) {
            this.name = graphName;
            return this;
        }

        Builder directionality(Directionality directionality) {
            this.directionality = directionality;
            return this;
        }

        GraphVizGraph build() {
            GraphVizGraph g = new GraphVizGraph(this);
            return g;
        }
    }
}
