package funmarket.marketad;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Document(collection = "marketads")
public class MarketAdModel {
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

    private String imageUrl;

    @Email
    private String email;

    private String phone;

    public MarketAdModel() {
    }

    public MarketAdModel(String title, String description, Integer priceCents, String email, String phone, String imageUrl) {
        this.title = title;
        this.description = description;
        this.priceCents = priceCents;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceCents() { return priceCents; }

    public String getEmail() {return email; }

    public String getPhone() {return phone; }

    public String getImageUrl() {return imageUrl;}

    @Override
    public String toString() {
        return "MarketAd [id=" + id + ", title=" + title + ", ...]";
    }
}
