package org.rastalion.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    @Null
    private UUID beerId;

    @Null
    private Integer version;

    /*
    Here we want the created date and Java has a few options.

    Usually when your dealing with dates on a web application
    that you're interfacing with, you typically wanna be doing a UTC type date.

    It's not a good practice to be doing his zone time and dates
    when your dealing with a computer type interfaces. It always throws a wrench in the work.

    Our OffsetDateTime which will include a UTC offset but we'll be doing everything based on UTC date.
     */
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @Positive
    @NotNull
    private Long upc;

    @Positive
    @NotNull
    private BigDecimal price;

    private Integer quantityOnHand;


}
