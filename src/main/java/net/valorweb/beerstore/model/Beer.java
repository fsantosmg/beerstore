package net.valorweb.beerstore.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "beers-1")
    private String  name;

    @NotNull(message = "beers-2")
    private BeerType type;

    @NotNull(message = "beers-3")
    @DecimalMin(value = "0", message = "beers-4")
    private BigDecimal volume;


}
