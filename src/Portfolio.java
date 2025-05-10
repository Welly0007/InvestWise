import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio implements Serializable {
    private Investor owner;
    private String name;
    private List<Asset> assets;
    private PerformancCalculator tracker;
    private RiskAllocation riskAssessor;
    private PortfolioDatabase portfolioDatabase;

    public Portfolio(Investor createdBy, PortfolioDatabase portfolioDatabase, String name) {
        this.owner = createdBy;
        this.portfolioDatabase = portfolioDatabase;
        this.name = name;
        this.assets = new ArrayList<>(); 
        portfolioDatabase.addData(this);
    }

    public Investor getOwner() {
        return this.owner;
    }
    public List<Asset> getAssets() {
        return assets;
    }
    public boolean addAsset() {
        Asset createdAsset = AssetFactory.createAsset();
        if (createdAsset == null) {
            System.out.println("Failed to create asset. Please try again.");
            return false;
        }
        Portfolio oldPortfolio = this;
        assets.add(createdAsset);
        portfolioDatabase.editData(oldPortfolio, this);
        System.out.println("Asset created successfully!");
        System.out.println("Asset details: " + createdAsset.toString());
        return true;
    }
    public Boolean removeAsset(Asset asset){
        if(assets.contains(asset)){
            Portfolio oldPortfolio = this;
            assets.remove(asset);
            portfolioDatabase.editData(oldPortfolio, this);
            return true;
        }
        else {
            return false;
        }
    }

    public  boolean editAsset(String Name){
        Asset assetToEdit = getAssetByName(Name);
        if(!(assetToEdit == null)){
            Portfolio oldPortfolio = this;
            assetToEdit.editAsset();
            portfolioDatabase.editData(oldPortfolio, this);
            return true;
        }
        else{
            return false;
        }
    }
    public void viewAssets(){
        if(assets.isEmpty()){
            System.out.println("No assets in the portfolio.");
        }else{
            System.out.println("Assets in the portfolio:");
            for(Asset asset : assets){
                System.out.println(asset.toString());
            }
        }
    }
    // Getter to help in getting specific asset
    public Asset getAssetByName(String name){
        for(Asset asset : assets){
            if(asset.getName().equals(name)){
                return asset;
            }
        }
        System.out.println("Asset not found in the portfolio.");
        return null;
    }

    public String toString(){
        return "Name: " + name;
    }

}
