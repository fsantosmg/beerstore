package net.valorweb.beerstore.resource;


import net.valorweb.beerstore.model.Beer;
import net.valorweb.beerstore.repository.Beers;
import net.valorweb.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> all(){
        return beerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer){
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable Long id, @Valid @RequestBody Beer beer){
        beer.setId(id);
       return beerService.save(beer);
    }
}
