package net.valorweb.beerstore.repository;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Beers extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
