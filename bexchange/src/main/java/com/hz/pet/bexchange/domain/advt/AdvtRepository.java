package com.hz.pet.bexchange.domain.advt;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Herman Zamula
 */
@RepositoryRestResource(collectionResourceDescription = @Description("Provides API for working with ads - creating, " +
        "querying, getting info etc."))
public interface AdvtRepository extends PagingAndSortingRepository<Advt, Long> {
}
