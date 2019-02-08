package net.valorweb.beerstore.service;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    public Beer save(Beer beer)  {
        throw new BeerAlreadyExistException();
    }
}
