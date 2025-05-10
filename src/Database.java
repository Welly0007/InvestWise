import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * An abstract database class that provides basic file-based CRUD operations
 * for serializable objects. Implements serialization for data persistence.
 *
 * @param <T> The type of objects stored in the database, must implement Serializable
 */
public abstract class Database<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String fileName;
    /**
     * Constructs a new Database instance with the specified file name.
     *
     * @param fileName The name of the file to use for data storage
     */
    public Database(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Loads data from the database file. If the file doesn't exist,
     * creates a new empty database file.
     *
     * @return A List containing all loaded data items
     * @throws RuntimeException if there's an error during file operations
     */ 
    protected List<T> loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Database file not found. Creating a new one...");
            saveToFile(new ArrayList<>()); // Create an empty database file
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    /**
     * Saves data to the database file.
     *
     * @param data The List of items to be saved
     * @throws RuntimeException if there's an error during file operations
     */
    protected void saveToFile(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Adds a new item to the database.
     *
     * @param item The item to add
     * @return true if the operation was successful, false otherwise
     */
    public abstract boolean addData(T item);
    /**
     * Deletes an item from the database.
     *
     * @param item The item to delete
     * @return true if the operation was successful, false otherwise
     */
    public abstract boolean deleteData(T item);
    /**
     * Edits an existing item in the database.
     *
     * @param oldData The item to be modified
     * @param newData The new version of the item
     * @return true if the operation was successful, false otherwise
     */
    public abstract boolean editData(T oldData, T NewData);
}
