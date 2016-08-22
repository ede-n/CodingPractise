package me.nave.visualize.graphviz

import spock.lang.Specification

/**
 * @author naveenede
 */
class GraphVizGraphTest extends Specification {

    def "GraphVizGraph prints dot representation of empty graph correctly"() {
        given:
        GraphVizGraph.Builder builder = new GraphVizGraph.Builder();
        String undirectedLRDot = """\
            graph G {
              rankdir = LR;
            }""".stripIndent();

        when:
        GraphVizGraph graphVizGraph = builder
                .type(Type.GRAPH)
                .name("G")
                .directionality(Directionality.LR)
                .build();

        then:
        graphVizGraph.toDot() == undirectedLRDot;
    }
}
