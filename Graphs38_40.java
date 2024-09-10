
import java.util.*;

public class Graphs38_40 {
    static class Edge {
        int src, dest, wt;
        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        graph[6].add(new Edge(6, 5, 1));
    }

    public static void bfs(ArrayList<Edge> graph[]) {
        boolean visit[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visit[i]) {
                bfsUtil(graph, i, visit);
            }
        }
    }

    public static void bfsUtil(ArrayList<Edge> graph[], int key, boolean visit[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(key);
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            if (!visit[curr]) {
                visit[curr] = true;
                System.out.print(curr + " ");
                for (int i = 0; i < graph[curr].size(); i++) {
                    q.add(graph[curr].get(i).dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[]) {
        boolean visit[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visit[i]) {
                dfsUtil(graph, i, visit);
            }
        }
    }

    public static void dfsUtil(ArrayList<Edge> graph[], int key, boolean visit[]) {
        if (visit[key]) {
            return;
        }
        System.out.print(key + " ");
        visit[key] = true;
        for (int i = 0; i < graph[key].size(); i++) {
            dfsUtil(graph, graph[key].get(i).dest, visit);
        }
    }

    public static boolean hasPath(ArrayList<Edge> graph[], int curr, 
                                    int key, boolean visit[]) {
        if (key == curr) {
            return true;
        }
        visit[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visit[e.dest] && hasPath(graph, e.dest, key, visit)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCycle(int v, ArrayList<Edge> graph[]) {
        boolean visit[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visit[i] && cycleutil(graph, i, -1, visit)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean cycleutil(ArrayList<Edge> g[], int curr, int parent, boolean visit[]) {
        visit[curr] = true;
        for (Edge edge : g[curr]) {
            int n = edge.dest;
            if (visit[n] && n != parent) {
                return true;
            } else if (!visit[n] && cycleutil(g, n, curr, visit)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBipartite(ArrayList<Edge> graph[]) {
        int color[] = new int[graph.length]; // Array to store colors assigned to vertices
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0 && !bipartiteUtil(graph, i, color, 1)) {
                return false; // If a vertex is found to be in the same set as its neighbor, the graph is not bipartite
            }
        }
        return true; // If no conflict is found, the graph is bipartite
    }

    private static boolean bipartiteUtil(ArrayList<Edge> graph[], int vertex, int color[], int currentColor) {
        color[vertex] = currentColor; // Assign color to the current vertex
        for (Edge edge : graph[vertex]) {
            int neighbor = edge.dest;
            if (color[neighbor] == currentColor) {
                return false; // If a neighbor has the same color as the current vertex, the graph is not bipartite
            } else if (color[neighbor] == 0) {
                // If the neighbor is not colored, recursively color it with the opposite color
                if (!bipartiteUtil(graph, neighbor, color, -currentColor)) {
                    return false; // If a conflict is found during recursion, the graph is not bipartite
                }
            }
        }
        return true; // If no conflict is found, the graph is bipartite
    }

    // find cycle in directed graph
    public static boolean isCyclic(int numVertices, ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[numVertices]; // Track visited vertices
        boolean stack[] = new boolean[numVertices];   // Track vertices in the current recursion stack
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && hasCycle(graph, visited, stack, i)) {
                return true;
            }
        }
        return false;
    }
   
    public static boolean hasCycle(ArrayList<Edge> graph[], boolean visited[], boolean stack[], int vertex) {
        visited[vertex] = true;
        stack[vertex] = true;
        for (Edge edge : graph[vertex]) {
            int neighbor = edge.dest;
            if (stack[neighbor]) {  // If the neighbor is already in the current recursion stack, a cycle is found
                return true;
            }
            if (!visited[neighbor] && hasCycle(graph, visited, stack, neighbor)) {
                return true;
            }
        }
        stack[vertex] = false; // Backtrack: remove the vertex from the recursion stack
        return false;
    }

    public static int[] topoSort(int numVertices, ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[numVertices]; // Track visited vertices
        Stack<Integer> stack = new Stack<>(); // Stack to store vertices in topological order
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topo(i, graph, visited, stack); // Perform DFS traversal
            }
        }
        int ans[] = new int[numVertices];
        int index = 0;
        while (!stack.isEmpty()) {
            ans[index++] = stack.pop(); // Pop vertices from the stack to get topological order
        }
        return ans;
    }
   
    public static void topo(int vertex, ArrayList<Edge> graph[], boolean visited[], Stack<Integer> stack) {
        visited[vertex] = true;
        for (Edge edge : graph[vertex]) {
            int neighbor = edge.dest;
            if (!visited[neighbor]) {
                topo(neighbor, graph, visited, stack); // Recursively explore unvisited neighbors
            }
        }
        stack.push(vertex); // Push the current vertex to the stack after exploring all neighbors
    }

    public static void inDeg(ArrayList<Edge> graph[]) {
        
    }

    public static void topoSort(ArrayList<Edge> graph[]) {

    }


    public static void main(String[] args) {
        int v = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        
    }
}
