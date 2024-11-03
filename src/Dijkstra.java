import java.util.*;


public class Dijkstra {
    private Map<String, List<Edge>> graph = new HashMap<>();
    public void addEdge(String source, String destination, int weight) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
    }

    public PathResult findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }

        distances.put(start, 0);
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentNode = current.name;

            if (currentNode.equals(end)) {
                break;
            }

            for (Edge edge : graph.getOrDefault(currentNode, Collections.emptyList())) {
                String neighbor = edge.destination;
                int newDistance = distances.get(currentNode) + edge.weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, currentNode);
                    queue.add(new Node(neighbor, newDistance));
                }
            }
        }


        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return new PathResult(path, distances.get(end));
    }


    private static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    public static class PathResult {
        List<String> path;
        int distance;

        PathResult(List<String> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
    }
}
