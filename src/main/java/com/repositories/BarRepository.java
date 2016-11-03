package com.repositories;

import org.springframework.data.repository.CrudRepository;
import com.models.Bar;

import java.util.List;

/**
 * Created by Menerith on 31-Oct-16.
 */
public interface BarRepository extends CrudRepository<Bar, String> {
    List<Bar> findByName(String name);

}
