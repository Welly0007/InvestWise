import java.io.Serializable;
import java.util.Date;
public abstract class Asset implements Serializable{
    private String name;
    private int quantity;
    private Date purchaseDate;
    private float purchasePrice;
    private Boolean zakatApplicable;
    
    public Asset createAsset(String assetType){
        
    }
}
