package funmarket.marketad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "marketads")
public class MarketAd {
    @Id
    private String id;
    private String title;
    private String description;
    private Integer priceCents;

    public MarketAd() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return title;
    }

    public Integer getPriceCents() { return priceCents; }

    @Override
    public String toString() {
        return "MarketAd [id=" + id + ", title=" + title + ", ...]";
    }
}
