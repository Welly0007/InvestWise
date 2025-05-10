import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an investment portfolio containing various assets.
 * Implements Serializable to support object serialization for persistence.
 * Each portfolio is associated with an investor and maintains a list of assets.
 */
public class Portfolio implements Serializable {
    private Investor owner;
    private String name;
    private List<Asset> assets;
    private PortfolioDatabase portfolioDatabase;

    /**
     * Constructs a new Portfolio with the specified owner, database reference, and name.
     * Automatically adds the new portfolio to the database.
     *
     * @param createdBy The investor who owns this portfolio
     * @param portfolioDatabase The database where the portfolio will be stored
     * @param name The name of the portfolio
     */
    public Portfolio(Investor createdBy, PortfolioDatabase portfolioDatabase, String name) {
        this.owner = createdBy;
        this.portfolioDatabase = portfolioDatabase;
        this.name = name;
        this.assets = new ArrayList<>();
        portfolioDatabase.addData(this);
    }

    /**
     * Gets the owner of this portfolio.
     *
     * @return The Investor who owns this portfolio
     */
    public Investor getOwner() {
        return this.owner;
    }

    /**
     * Gets the list of assets in this portfolio.
     *
     * @return A List of Asset objects in the portfolio
     */
    public List<Asset> getAssets() {
        return assets;
    }

    /**
     * Adds a new asset to the portfolio using the AssetFactory.
     * Updates the portfolio in the database after successful addition.
     *
     * @return true if the asset was added successfully, false otherwise
     */
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

    /**
     * Removes an asset from the portfolio.
     * Updates the portfolio in the database after successful removal.
     *
     * @param asset The asset to be removed
     * @return true if the asset was removed successfully, false otherwise
     */
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

    /**
     * Edits an existing asset in the portfolio by name.
     * Updates the portfolio in the database after successful edit.
     *
     * @param Name The name of the asset to edit
     * @return true if the asset was edited successfully, false otherwise
     */
    public boolean editAsset(String Name){
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

    /**
     * Displays all assets in the portfolio to the console.
     * Shows a message if the portfolio is empty.
     */
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

    /**
     * Retrieves an asset from the portfolio by its name.
     *
     * @param name The name of the asset to find
     * @return The Asset object if found, null otherwise
     */
    public Asset getAssetByName(String name){
        for(Asset asset : assets){
            if(asset.getName().equals(name)){
                return asset;
            }
        }
        System.out.println("Asset not found in the portfolio.");
        return null;
    }

    /**
     * Returns a string representation of the portfolio.
     *
     * @return A string containing the portfolio name
     */
    public String toString(){
        return "Name: " + name;
    }
}