import java.util.ArrayList;

public class DynamicHashTableExample {
    private ArrayList<Integer> hashTable;
    private int capacity;
    private int size;

    public DynamicHashTableExample(int initialCapacity) {
        capacity = initialCapacity;
        hashTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            hashTable.add(null); // Inicializamos la tabla con null
        }
        size = 0;
    }

    public void insertar(int clave) {
        if (size >= capacity * 0.7) { // Si el factor de carga es mayor al 70%
            redimensionar();
        }
        int index = clave % capacity;
        while (hashTable.get(index) != null) {
            index = (index + 1) % capacity; // Sondeo Lineal en caso de colisión
        }
        hashTable.set(index, clave);
        size++;
    }

    private void redimensionar() {
        System.out.println("Redimensionando la tabla...");
        capacity *= 2; // Duplicar la capacidad
        ArrayList<Integer> newHashTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            newHashTable.add(null);
        }

        // Reinsertar los elementos en la nueva tabla
        for (Integer element : hashTable) {
            if (element != null) {
                int index = element % capacity;
                while (newHashTable.get(index) != null) {
                    index = (index + 1) % capacity;
                }
                newHashTable.set(index, element);
            }
        }

        hashTable = newHashTable; // Reemplazar la tabla vieja por la nueva
    }

    public void mostrar() {
        System.out.println(hashTable);
    }

    public static void main(String[] args) {
        DynamicHashTableExample hashTable = new DynamicHashTableExample(4);
        hashTable.insertar(5);
        hashTable.insertar(10);
        hashTable.insertar(15);
        hashTable.insertar(20);
        hashTable.insertar(25); // Esto causará un redimensionamiento
        hashTable.mostrar(); // Ver la tabla después del redimensionamiento
    }
}
