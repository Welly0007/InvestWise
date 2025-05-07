import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Database<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String fileName;
    protected long numItems;

    public Database(String fileName) {
        this.fileName = fileName;
    }

    protected List<T> loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    protected void saveToFile(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public abstract boolean addData(T item);

    public abstract boolean deleteData(T item);
    public abstract boolean editData(T oldData, T NewData);
}
