import java.util.*;

// 1. EL NODO (VÉRTICE) - Igual de simple
class Vertex {
    private int id;
    private String nombre;

    public Vertex(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
}

// 2. LA CONEXIÓN (ARISTA) - Igual de simple
class Edge {
    private Vertex origen;
    private Vertex destino;
    private int peso; 

    public Edge(Vertex origen, Vertex destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    public Vertex getDestino() { return destino; }
}

// 3. LA ESTRUCTURA PRINCIPAL (Optimizada con Diccionario/Map)
class Grafo {
    private Map<Vertex, List<Edge>> adyacencias = new HashMap<>();

    public void agregarVertice(Vertex v) { 
        adyacencias.putIfAbsent(v, new ArrayList<>()); 
    }

    public void agregarArista(Edge e) { 
        adyacencias.get(e.getOrigen()).add(e); 
    }

    // --- ALGORITMO 1: DFS (Profundidad) ---
    public void dfs(Vertex inicio) {
        System.out.println("\n--- DFS (Profundidad) ---");
        dfsRecursivo(inicio, new HashSet<>());
    }

    private void dfsRecursivo(Vertex v, Set<Vertex> visitados) {
        if (!visitados.contains(v)) {
            visitados.add(v);
            System.out.print(v.getNombre() + " -> ");

            for (Edge e : adyacencias.get(v)) {
                dfsRecursivo(e.getDestino(), visitados);
            }
        }
    }

    // --- ALGORITMO 2: BFS (Anchura) ---
    public void bfs(Vertex inicio) {
        System.out.println("\n\n--- BFS (Anchura) ---");
        Set<Vertex> visitados = new HashSet<>();
        Queue<Vertex> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Vertex v = cola.poll(); 
            System.out.print(v.getNombre() + " -> ");

            for (Edge e : adyacencias.get(v)) {
                if (!visitados.contains(e.getDestino())) {
                    visitados.add(e.getDestino());
                    cola.add(e.getDestino()); 
                }
            }
        }
    }
}

public class MainGrafos {
    public static void main(String[] args) {
        Grafo miGrafo = new Grafo();

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        miGrafo.agregarVertice(v1);
        miGrafo.agregarVertice(v2);
        miGrafo.agregarVertice(v3);
        miGrafo.agregarVertice(v4);

        miGrafo.agregarArista(new Edge(v1, v2, 5)); 
        miGrafo.agregarArista(new Edge(v1, v3, 2)); 
        miGrafo.agregarArista(new Edge(v2, v4, 1)); 
        miGrafo.agregarArista(new Edge(v3, v4, 8)); 

        miGrafo.dfs(v1); 
        miGrafo.bfs(v1); 
    }
}
