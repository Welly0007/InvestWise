import java.util.List;
import java.io.Serializable;
public class Portfolio implements Serializable{
    private
    Investor owner;
    List<Asset> assets;
    PerformancCalculator tracker;
    RiskAllocation riskAssessor;
    public
    Portfolio(Investor createdBy){
        this.owner = createdBy;
    };
    Portfolio getByOwner(Investor owner){
        if (this.owner.equals(owner)){
            return this;
        }else{
            return null;
        }
    }
    public Investor getOwner() {
        return this.owner;
    }
}
