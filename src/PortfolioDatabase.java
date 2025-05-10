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
            ports.add(newPort);
            saveToFile(ports); // Save the updated list to the file
            return true;
        } else {
            return false;
        }
    }

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