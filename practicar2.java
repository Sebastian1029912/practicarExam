import java.util.LinkedList;

// 1. CLASE PARA SONDEO LINEAL
class HashTableLinear {
    private Integer[] table;
    private int size;

    public HashTableLinear(int size) {
        this.size = size;
        this.table = new Integer[size];
    }

    // Función hash: módulo
    private int hash(int key) {
        return key % size;
    }

    // Insertar clave usando sondeo lineal
    public void insert(int key) {
        int index = hash(key); // Calcular posición base
        int startIndex = index;

        while (table[index] != null) { // Si hay colisión
            System.out.println("Colisión en índice " + index + " para clave " + key);
            index = (index + 1) % size; // Ir a la siguiente posición
            if (index == startIndex) {
                System.out.println("Tabla llena. No se pudo insertar " + key);
                return;
            }
        }
        table[index] = key;
    }

    // Mostrar tabla
    public void printTable() {
        System.out.println("\n-- Hash Table (Sondeo Lineal) --");
        for (int i = 0; i < size; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }
}

// 2. CLASE PARA ENCADENAMIENTO
class HashTableChaining {
    private LinkedList<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableChaining(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) { // Función hash
        return key % size;
    }

    // Insertar clave usando encadenamiento
    public void insert(int key) {
        int index = hash(key);
        table[index].add(key); // Agregar a la lista correspondiente
    }

    // Mostrar tabla
    public void printTable() {
        System.out.println("\n-- Hash Table (Encadenamiento) --");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            for (int val : table[i]) {
                System.out.print(val + " -> ");
            }
            System.out.println();
        }
    }
}

// 3. CLASE PRINCIPAL QUE EJECUTA AMBAS PRUEBAS
public class HashTable1 {
    public static void main(String[] args) {
        // Claves del ejemplo de la guía
        int[] claves = {34, 77, 25, 16, 45, 15, 25};

        // Crear tabla hash con sondeo lineal
        HashTableLinear linearHash = new HashTableLinear(10);
        for (int clave : claves) {
            linearHash.insert(clave); // Insertar clave
        }
        linearHash.printTable(); // Mostrar tabla

        // Crear tabla hash con encadenamiento
        HashTableChaining chainingHash = new HashTableChaining(10);
        for (int clave : claves) {
            chainingHash.insert(clave); // Insertar clave
        }
        chainingHash.printTable(); // Mostrar tabla
    }
}
