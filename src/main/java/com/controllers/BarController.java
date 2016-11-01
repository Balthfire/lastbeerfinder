package com.controllers;

import com.models.Bar;
import com.repositories.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Menerith on 31-Oct-16.
 */

@RestController
@RequestMapping("/bars")
public class BarController {

    @Autowired
    private BarRepository BarRepo;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBars() {
        try{
            return new ResponseEntity<>(BarRepo.findAll(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Récupération d'un bar avec son ID
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getBarWithId(@PathVariable String id) {
        try{
            return new ResponseEntity<>(BarRepo.findOne(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
