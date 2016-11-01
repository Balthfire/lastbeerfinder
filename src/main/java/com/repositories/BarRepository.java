package com.repositories;

import org.springframework.data.repository.CrudRepository;
import com.models.Bar;

/**
 * Created by Menerith on 31-Oct-16.
 */
public interface BarRepository extends CrudRepository<Bar, String> {


}
