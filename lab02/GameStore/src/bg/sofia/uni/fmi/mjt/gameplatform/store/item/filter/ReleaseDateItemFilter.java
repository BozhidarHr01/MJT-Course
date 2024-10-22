package bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;

import java.time.LocalDateTime;

public class ReleaseDateItemFilter implements ItemFilter{
    LocalDateTime lowerBound;
    LocalDateTime upperBound;

    public ReleaseDateItemFilter(LocalDateTime lowerBound, LocalDateTime upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean matches(StoreItem item){
        LocalDateTime releaseDate = item.getReleaseDate();
        return (releaseDate.isAfter(lowerBound) || releaseDate.isEqual(lowerBound))
                && (releaseDate.isBefore(upperBound) || releaseDate.isEqual(upperBound));
    }
}
