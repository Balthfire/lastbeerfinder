package com.repositories;

import com.models.Beer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Menerith on 31-Oct-16.
 */
public interface BeerRepository extends CrudRepository<Beer,String>{
}
