package funmarket.marketad;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketAdRepository extends MongoRepository<MarketAd, String> {
}
