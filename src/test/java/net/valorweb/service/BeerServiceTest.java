package net.valorweb.service;

import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.model.BeerType;
import net.valorweb.beerstore.repository.Beers;
import net.valorweb.beerstore.service.BeerService;
import net.valorweb.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;


public class BeerServiceTest {

    @Mock
    private Beers beers;

    @InjectMocks
    private BeerService beerService;

    @Before
    public void setup(){


        MockitoAnnotations.initMocks(this);

    }



    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists(){

        Beer beerInDataBase = new Beer();
        beerInDataBase.setName("Heineken");
        beerInDataBase.setType(BeerType.LAGER);
        beerInDataBase.setVolume(new BigDecimal(355));

        when(beers.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDataBase));

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

        Beer newBeerInDataBase = new Beer();
        newBeerInDataBase.setId(10L);
        newBeerInDataBase.setName("Heineken");
        newBeerInDataBase.setType(BeerType.LAGER);
        newBeerInDataBase.setVolume(new BigDecimal(355));

        when(beers.save(newBeer)).thenReturn(newBeerInDataBase);
        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
