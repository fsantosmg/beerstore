package net.valorweb.beerstore.repository;

import net.valorweb.beerstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Beers extends JpaRepository<Beer, Long> {
}
