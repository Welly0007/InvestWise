import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Portfolio implements Serializable {
    private Investor owner;
    private List<Asset> assets;
    private PerformancCalculator tracker;
    private RiskAllocation riskAssessor;

    public Portfolio(Investor createdBy) {
        this.owner = createdBy;
    }

    public Investor getOwner() {
        return this.owner;
    }
    public void addAsset(){
        System.out.println("Enter asset type: ");
        Scanner scanner = new Scanner(System.in);
        String assetType = scanner.nextLine();
        System.out.println("Creating asset with type: " + assetType +" ...");
        Thread.sleep(2000);
        Asset createdAsset = Asset.createAsset(assetType);
        if(!(createdAsset.equals(null)))
            assets.add(createdAsset);
        else
            System.out.println("Problem occured while creating asset");
    }
}
