package com.controllers;

import com.models.Bar;
import com.repositories.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Menerith on 31-Oct-16.
 */

@RestController
@RequestMapping("/bars")
public class BarController {

    @Autowired
    private BarRepository BarRepo;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getAllBars() {
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
     * @param idbar
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{idbar}")
    public @ResponseBody ResponseEntity<?> getBarWithId(@PathVariable String idbar) {
        try{
            return new ResponseEntity<>(BarRepo.findOne(idbar),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idbar}")
    public @ResponseBody ResponseEntity<?> delBar(@PathVariable String idbar) {
        ResponseEntity<String> response = null;
        try {
            BarRepo.delete(idbar);
            response = new ResponseEntity<String>(HttpStatus.GONE);
        }catch (Exception e){
            response= new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        }
        return  response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idbar}/beers")
    public @ResponseBody ResponseEntity<?> getBeersWithBarId(@PathVariable String idbar) {
        try{
            return new ResponseEntity<>(BarRepo.findOne(idbar).getBeers(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/terrasse")
    public @ResponseBody ResponseEntity<?> getBarsWithTerrasse() {
        try{
            List<Bar> lstBarTerrasse = new ArrayList<>();

            for (Bar lebar: BarRepo.findAll())
            {
                if (lebar.isTerrasse())
                    lstBarTerrasse.add(lebar);
            }
            return new ResponseEntity<>(lstBarTerrasse,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }


}
