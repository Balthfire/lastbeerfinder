package com.repositories;

import com.models.Beer;
import com.models.Origin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Menerith on 03-Nov-16.
 */
public interface OriginRepository extends CrudRepository<Origin,Integer> {
}
