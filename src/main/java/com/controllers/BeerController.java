package com.controllers;

import com.models.Beer;
import com.models.Origin;
import com.models.Type;
import com.repositories.BarRepository;
import com.repositories.BeerRepository;
import com.repositories.OriginRepository;
import com.repositories.TypeRepository;
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
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerRepository BeerRepo;
    @Autowired
    private OriginRepository OriginRepo;
    @Autowired
    private TypeRepository TypeRepo;


    /* Renvoie toute les bières */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getAllBeers() {
        try{
            return new ResponseEntity<>(BeerRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }


    /* Renvoie une bière en fonction de l'idbeer passé en paramètre */
    @RequestMapping(method = RequestMethod.GET, value = "/{idbeer}")
    public @ResponseBody ResponseEntity<?> getBeerWithId(@PathVariable String idbeer) {
        try{
            return new ResponseEntity<>(BeerRepo.findOne(idbeer),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /* Renvoie toute les bières en fonction de l'idorigin passé en paramètre */
    @RequestMapping(method = RequestMethod.GET, value = "/origin/{idorigin}")
    public @ResponseBody ResponseEntity<?> getBeersWithOrigin(@PathVariable int idorigin) {
        try{

            Origin beerorigin = OriginRepo.findOne(idorigin);

            return new ResponseEntity<>(beerorigin.getBeers(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /* Renvoie toute les bières en fonction de l'idtype passé en paramètre */
    @RequestMapping(method = RequestMethod.GET, value = "/type/{idtype}")
    public @ResponseBody ResponseEntity<?> getBeersWithType(@PathVariable int idtype) {
        try{

            Type beertype = TypeRepo.findOne(idtype);

            return new ResponseEntity<>(beertype.getBeers(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /* Renvoie toute les bières en fonction de l'idtype et l'idorigin passé en paramètre */
    @RequestMapping(method = RequestMethod.GET, value = "/type/{idtype}/origin/{idorigin}")
    public @ResponseBody ResponseEntity<?> getBeersWithTypeOrigin(@PathVariable("idtype") int idtype,
                                                                  @PathVariable("idorigin") int idorigin) {
        try{
            Type beertype = TypeRepo.findOne(idtype);
            Origin beerorigin = OriginRepo.findOne(idorigin);

            List<Beer> lstBeerType = beertype.getBeers();
            List<Beer> lstBeerOrigin = beerorigin.getBeers();
            List<Beer> lstBeerTypeOrigin = new ArrayList<>();

            for (Beer beerT: lstBeerType)
            {
                for (Beer beerO: lstBeerOrigin) {
                    if (beerT == beerO)
                    {
                        lstBeerTypeOrigin.add(beerT);
                    }
                }
            }
            return new ResponseEntity<>(lstBeerTypeOrigin,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
