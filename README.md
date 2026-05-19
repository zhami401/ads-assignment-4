# Graph Algorithms: BFS & Dijkstra

Implementation of Breadth-First Search and Dijkstra's algorithm on a weighted graph where each `Vertex` stores its own adjacency map instead of using a separate `Edge` class.

## Structure

```
src/
├── Vertex.java               # Graph node with adjacency map
├── WeightedGraph.java        # Graph container, manages vertices and edges
├── Search.java               # Abstract base: visited tracking & path reconstruction
├── BreadthFirstSearch.java   # BFS — shortest path by number of edges
├── DijkstraSearch.java       # Dijkstra — shortest path by total weight
└── Main.java                 # Demo with Kazakhstan cities graph
```

## Key Design

Each vertex stores its neighbors and edge weights directly:

```java
public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
}
```

## How to Run

```bash
cd src
javac *.java
java Main
```

Requires Java 17+.

## Example Output

```
BFS path to Aktobe:       Almaty → Astana → Aktobe          (2 hops)
Dijkstra path to Aktobe:  Almaty → Astana → Karaganda → Aktobe  [2700 km]
```

BFS finds the fewest hops; Dijkstra finds the lowest total distance.
