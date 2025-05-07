import java.util.List;
import java.util.ArrayList;
public class PortfolioDatabase extends Database<Portfolio>{
    private static final long serialVersionUID = 1L;
    private List<Portfolio> ports;
    public PortfolioDatabase(){
        super("src/database/portfolioDatabase.ser");
        this.ports = loadFromFile();
    }

    @Override
    public boolean addData(Portfolio newPort) {
        if (!ports.contains(newPort)) {
            this.ports.add(newPort);
            saveToFile(ports);
            numItems++;
            return true;
        } else {
            System.out.println("User already exists in the database.");
            return false;
        }
    }

    @Override
    public boolean deleteData(Portfolio port) {
        if (ports.contains(port)) {
            ports.remove(port);
            saveToFile(ports);
            numItems--;
            return true;
        } else {
            System.out.println("Portfolio not found in the database.");
            return false;
        }
    }
    @Override
    public boolean editData(Portfolio oldData, Portfolio newData){
        deleteData(oldData);
        addData(newData);
        return true;
    }
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