package funmarket;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "marketads")
public class MarketAd {
    @Id
    private String id;
    private String title;
    private String description;

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

    @Override
    public String toString() {
        return "MarketAd [id=" + id + ", title=" + title + ", ...]";
    }
}
