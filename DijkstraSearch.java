import java.util.*;

public class DijkstraSearch<V> extends Search<V> {

    private final Map<Vertex<V>, Double> distances;

    public DijkstraSearch(Vertex<V> source) {
        super(source);
        this.distances = new HashMap<>();
        dijkstra(source);
    }

    private void dijkstra(Vertex<V> start) {
        PriorityQueue<Map.Entry<Vertex<V>, Double>> pq = new PriorityQueue<>(
            Comparator.comparingDouble(Map.Entry::getValue)
        );

        distances.put(start, 0.0);
        pq.add(Map.entry(start, 0.0));

        while (!pq.isEmpty()) {
            Map.Entry<Vertex<V>, Double> current = pq.poll();
            Vertex<V> currentVertex = current.getKey();
            double currentDist = current.getValue();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            for (Map.Entry<Vertex<V>, Double> neighborEntry :
                    currentVertex.getAdjacentVertices().entrySet()) {

                Vertex<V> neighbor = neighborEntry.getKey();
                double edgeWeight = neighborEntry.getValue();

                if (visited.contains(neighbor)) {
                    continue;
                }

                double newDist = currentDist + edgeWeight;
                double oldDist = distances.getOrDefault(neighbor, Double.MAX_VALUE);

                if (newDist < oldDist) {
                    distances.put(neighbor, newDist);
                    parent.put(neighbor, currentVertex);
                    pq.add(Map.entry(neighbor, newDist));
                }
            }
        }
    }

    public double distanceTo(Vertex<V> target) {
        return distances.getOrDefault(target, Double.MAX_VALUE);
    }

    public Map<Vertex<V>, Double> getDistances() {
        return distances;
    }
}
