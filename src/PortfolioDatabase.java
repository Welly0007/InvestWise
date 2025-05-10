import java.util.List;
import java.util.ArrayList;

/**
 * A database implementation for managing Portfolio objects.
 * Extends the generic Database class with Portfolio-specific operations.
 * Handles persistence of portfolio data to a serialized file.
 */
public class PortfolioDatabase extends Database<Portfolio> {
    private static final long serialVersionUID = 1L;
    private List<Portfolio> ports;

    /**
     * Constructs a new PortfolioDatabase.
     * Initializes the database by loading existing portfolios from the serialized file.
     * Uses "src/database/portfolioDatabase.ser" as the storage file.
     */
    public PortfolioDatabase() {
        super("src/database/portfolioDatabase.ser");
        this.ports = loadFromFile();
    }

    /**
     * Adds a new portfolio to the database if it doesn't already exist.
     * 
     * @param newPort The portfolio to add
     * @return true if the portfolio was added successfully, false if it already exists
     */
    @Override
    public boolean addData(Portfolio newPort) {
        if (!ports.contains(newPort)) {
            ports.add(newPort);
            saveToFile(ports); // Save the updated list to the file
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a portfolio from the database.
     * 
     * @param port The portfolio to remove
     * @return true if the portfolio was removed successfully, false if it wasn't found
     */
    @Override
    public boolean deleteData(Portfolio port) {
        if (ports.contains(port)) {
            ports.remove(port);
            saveToFile(ports);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edits a portfolio by replacing the old version with the new version.
     * 
     * @param oldData The portfolio to be replaced
     * @param newData The new version of the portfolio
     * @return Always returns true (consider adding error handling)
     */
    @Override
    public boolean editData(Portfolio oldData, Portfolio newData) {
        deleteData(oldData);
        addData(newData);
        return true;
    }

    /**
     * Retrieves all portfolios belonging to a specific investor.
     * 
     * @param owner The investor whose portfolios to find
     * @return A list of portfolios owned by the specified investor
     */
    public List<Portfolio> getUserPortfolios(Investor owner) {
        List<Portfolio> foundPorts = new ArrayList<>();
        for (Portfolio port : ports) {
            if (port.getOwner().equals(owner)) {
                foundPorts.add(port);
            }
        }
        return foundPorts;
    }
    
}