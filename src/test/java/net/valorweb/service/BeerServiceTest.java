package net.valorweb.service;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.model.BeerType;
import net.valorweb.beerstore.service.BeerService;
import net.valorweb.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeerServiceTest {

    @Mock

    private BeerService beerService;

    @Before
    public void setup(){
        beerService = new BeerService();
    }



    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists(){

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(355));

        beerService.save(newBeer);

    }

    @Test
    public void should_create_new_beer(){

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(355));

       Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
