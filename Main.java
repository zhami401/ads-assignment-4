import java.util.List;

public class Main {

    public static void main(String[] args) {

        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Aktobe");
        graph.addVertex("Karaganda");

        graph.addEdge("Almaty",   "Astana",    1300);
        graph.addEdge("Almaty",   "Shymkent",   700);
        graph.addEdge("Astana",   "Karaganda",  200);
        graph.addEdge("Astana",   "Aktobe",    1500);
        graph.addEdge("Shymkent", "Karaganda",  900);
        graph.addEdge("Karaganda","Aktobe",    1200);

        Vertex<String> almaty = graph.getVertex("Almaty");
        Vertex<String> aktobe = graph.getVertex("Aktobe");

        System.out.println("════════════════════════════════════════");
        System.out.println("  BFS (обход в ширину) от Алматы");
        System.out.println("════════════════════════════════════════");

        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(almaty);

        for (String cityName : List.of("Astana", "Shymkent", "Karaganda", "Aktobe")) {
            Vertex<String> target = graph.getVertex(cityName);
            List<Vertex<String>> path = bfs.pathTo(target);
            System.out.print("Путь до " + cityName + ": ");
            System.out.println(formatPath(path));
        }

        System.out.println();
        System.out.println("════════════════════════════════════════");
        System.out.println("  ДЕЙКСТРА от Алматы");
        System.out.println("════════════════════════════════════════");

        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(almaty);

        for (String cityName : List.of("Astana", "Shymkent", "Karaganda", "Aktobe")) {
            Vertex<String> target = graph.getVertex(cityName);
            List<Vertex<String>> path = dijkstra.pathTo(target);
            double distance = dijkstra.distanceTo(target);
            System.out.printf("Кратчайший путь до %-12s: %s  [%.0f км]%n",
                cityName, formatPath(path), distance);
        }

        System.out.println();
        System.out.println("════════════════════════════════════════");
        System.out.println("  СРАВНЕНИЕ: BFS vs Дейкстра до Актобе");
        System.out.println("════════════════════════════════════════");

        List<Vertex<String>> bfsPath  = bfs.pathTo(aktobe);
        List<Vertex<String>> dijkPath = dijkstra.pathTo(aktobe);

        System.out.println("BFS (меньше рёбер):         " + formatPath(bfsPath));
        System.out.println("Дейкстра (минимум км):      " + formatPath(dijkPath));
        System.out.printf("Расстояние по Дейкстре:     %.0f км%n", dijkstra.distanceTo(aktobe));
    }

    private static String formatPath(List<Vertex<String>> path) {
        if (path.isEmpty()) return "путь не найден";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getData());
            if (i < path.size() - 1) sb.append(" → ");
        }
        return sb.toString();
    }
}
