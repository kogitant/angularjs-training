package funmarket.marketad;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Document(collection = "marketads")
public class MarketAd {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Min(5)
    @Max(10000000)
    private Integer priceCents;

    private String email;
    private String phone;

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

    public String getEmail() {return email; }

    public String getPhone() {return phone; }

    @Override
    public String toString() {
        return "MarketAd [id=" + id + ", title=" + title + ", ...]";
    }
}
