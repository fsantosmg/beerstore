package net.valorweb.beerstore.service;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.repository.Beers;
import net.valorweb.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    Beers beers;

    public Beer save(final Beer beer) {

        verifyIfBeerExists(beer);
        return beers.save(beer);

    }

    private void verifyIfBeerExists(final Beer beer) {
        Optional<Beer> beerByNameAndType = beers.findByNameAndType
                (beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() ||
                isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer,
                                               Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get()
                .equals(beer);
    }

    public List<Beer> findAll(){

        return beers.findAll();
    }


}
