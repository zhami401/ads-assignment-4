import java.util.*;

public abstract class Search<V> {

    protected final Vertex<V> source;
    protected final Set<Vertex<V>> visited;
    protected final Map<Vertex<V>, Vertex<V>> parent;

    public Search(Vertex<V> source) {
        this.source = source;
        this.visited = new HashSet<>();
        this.parent = new HashMap<>();
    }

    public List<Vertex<V>> pathTo(Vertex<V> target) {
        if (!visited.contains(target)) {
            return Collections.emptyList();
        }

        LinkedList<Vertex<V>> path = new LinkedList<>();
        Vertex<V> current = target;

        while (current != null && !current.equals(source)) {
            path.addFirst(current);
            current = parent.get(current);
        }

        path.addFirst(source);

        return path;
    }

    public boolean hasPathTo(Vertex<V> vertex) {
        return visited.contains(vertex);
    }
}
