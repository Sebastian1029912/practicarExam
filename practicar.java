import java.util.*;

// 1. EL NODO (VÉRTICE)
class Vertex {
    private int id;
    private String nombre;

    public Vertex(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
}

// 2. LA CONEXIÓN (ARISTA)
class Edge {
    private Vertex origen;
    private Vertex destino;
    private int peso; // Agregado para que Dijkstra funcione

    public Edge(Vertex origen, Vertex destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    public Vertex getOrigen() { return origen; }
    public Vertex getDestino() { return destino; }
    public int getPeso() { return peso; }
}

// 3. LA ESTRUCTURA PRINCIPAL Y LOS ALGORITMOS
class Grafo {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> aristas = new ArrayList<>();

    public void agregarVertice(Vertex v) { vertices.add(v); }
    public void agregarArista(Edge e) { aristas.add(e); }

    // -> EL MÉTODO FANTASMA QUE FALTABA EN EL PPT <-
    // Busca todas las flechas (aristas) que salen de un vértice específico
    public List<Edge> obtenerAristasDesde(Vertex v) {
        List<Edge> adyacentes = new ArrayList<>();
        for (Edge e : aristas) {
            if (e.getOrigen() == v) {
                adyacentes.add(e);
            }
        }
        return adyacentes;
    }

    // --- ALGORITMO 1: BÚSQUEDA EN PROFUNDIDAD (DFS) ---
    // Regla para el examen: Usa RECURSIVIDAD.
    public void dfs(Vertex inicio) {
        System.out.println("\n--- Iniciando DFS (Profundidad) ---");
        Set<Vertex> visitados = new HashSet<>();
        dfsRecursivo(inicio, visitados);
    }

    private void dfsRecursivo(Vertex v, Set<Vertex> visitados) {
        if (!visitados.contains(v)) {
            visitados.add(v);
            System.out.print(v.getNombre() + " -> "); // Imprime el nodo actual
            
            // Llama a la recursividad para los vecinos
            for (Edge e : obtenerAristasDesde(v)) {
                dfsRecursivo(e.getDestino(), visitados);
            }
        }
    }

    // --- ALGORITMO 2: BÚSQUEDA EN ANCHURA (BFS) ---
    // Regla para el examen: Usa un BUCLE WHILE y una COLA (Queue).
    public void bfs(Vertex inicio) {
        System.out.println("\n\n--- Iniciando BFS (Anchura) ---");
        Set<Vertex> visitados = new HashSet<>();
        Queue<Vertex> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Vertex v = cola.poll(); // Saca el primero de la fila
            System.out.print(v.getNombre() + " -> ");
            
            for (Edge e : obtenerAristasDesde(v)) {
                if (!visitados.contains(e.getDestino())) {
                    visitados.add(e.getDestino());
                    cola.add(e.getDestino()); // Pone a los vecinos en la fila
                }
            }
        }
    }
}

// 4. CLASE MAIN PARA PROBAR QUE TODO FUNCIONA
public class MainGrafos {
    public static void main(String[] args) {
        Grafo miGrafo = new Grafo();

        // Creamos 4 vértices
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        miGrafo.agregarVertice(v1);
        miGrafo.agregarVertice(v2);
        miGrafo.agregarVertice(v3);
        miGrafo.agregarVertice(v4);

        // Conectamos los vértices (Origen, Destino, Peso)
        miGrafo.agregarArista(new Edge(v1, v2, 5)); // A apunta a B
        miGrafo.agregarArista(new Edge(v1, v3, 2)); // A apunta a C
        miGrafo.agregarArista(new Edge(v2, v4, 1)); // B apunta a D
        miGrafo.agregarArista(new Edge(v3, v4, 8)); // C apunta a D

        // Ejecutamos las búsquedas
        miGrafo.dfs(v1); // A -> B -> D -> C
        miGrafo.bfs(v1); // A -> B -> C -> D
    }
}
