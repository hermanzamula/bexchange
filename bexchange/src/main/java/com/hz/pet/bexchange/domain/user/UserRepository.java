package com.hz.pet.bexchange.domain.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author Herman Zamula
 */
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource
    User findByPhoneNumber(String username);
}
