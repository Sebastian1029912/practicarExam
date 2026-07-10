import java.util.Arrays;

public class TablaHashDinamica {
    
    // TRUCO 1: Usar un arreglo nativo (Integer[]) en vez de ArrayList
    private Integer[] tabla; 
    private int capacidad;
    private int size;

    public TablaHashDinamica(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tabla = new Integer[capacidad]; // Ya nace lleno de 'null' automáticamente
        this.size = 0;
    }

    // 1. MÉTODO DE INSERCIÓN BÁSICO
    public void insertar(int clave) {
        if (size >= capacidad * 0.7) { 
            redimensionar();
        }
        
        int index = clave % capacidad;
        
        while (tabla[index] != null) { 
            index = (index + 1) % capacidad;
        }

        tabla[index] = clave;
        size++;
    }

    // 2. EL REDIMENSIONAMIENTO (VERSIÓN INTELIGENTE)
    private void redimensionar() {
        System.out.println("Redimensionando la tabla...");
        
        Integer[] tablaVieja = tabla;
        
        capacidad *= 2; 
        tabla = new Integer[capacidad];
        size = 0; )

        for (Integer claveAntigua : tablaVieja) {
            if (claveAntigua != null) {
                insertar(claveAntigua); // ¡Magia! Nos ahorramos volver a escribir el While del sondeo
            }
        }
    }

    public void mostrar() {
        System.out.println(Arrays.toString(tabla));
    }

    public static void main(String[] args) {
        TablaHashDinamica tablaDinamica = new TablaHashDinamica(4);
        tablaDinamica.insertar(5);
        tablaDinamica.insertar(10);
        tablaDinamica.insertar(15);
        tablaDinamica.insertar(20);
        tablaDinamica.insertar(25); // Aquí explotará la redimensión
        tablaDinamica.mostrar();
    }
}
