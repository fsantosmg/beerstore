package net.valorweb.beerstore.service;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.repository.Beers;
import net.valorweb.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    Beers beers;

    public Beer save(final Beer beer) {

        Optional<Beer> beerByNameAndType = beers.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent()) {
            throw new BeerAlreadyExistException();
        }

        return beers.save(beer);

    }


}
