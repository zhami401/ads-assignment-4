import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {

    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V data1, V data2, double weight) {
        Vertex<V> v1 = vertices.get(data1);
        Vertex<V> v2 = vertices.get(data2);

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException(
                "Оба вершины должны существовать перед добавлением рёбра. " +
                "Отсутствует: " + (v1 == null ? data1 : data2)
            );
        }

        v1.addAdjacentVertex(v2, weight);
        v2.addAdjacentVertex(v1, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}
