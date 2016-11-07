package com.controllers;

import com.models.Bar;
import com.repositories.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Menerith on 31-Oct-16.
 */
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
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

    @RequestMapping(method = RequestMethod.GET, value ="/barplaces")
    public ResponseEntity<?> getAllPlaces() {

        // 8 rue de la grange #EPSI
        Double latBase = 45.770903;
        Double longBase = 4.804082;

        try {
            GooglePlaces client = new GooglePlaces("AIzaSyAm9JjC92Vg0ksWA1IEzbR944NpUczCc04");

            List<Place> places = client.getNearbyPlaces(latBase, longBase, 1500.00, 20, Param.name("types").value("bar"));
            List<Bar> lstBar = new ArrayList<>();

            for (Place p : places)
            {
                Bar b = new Bar(p.getPlaceId(), p.getName(), p.getVicinity(),false,"happy_hours","open_hours", p.getLongitude(), p.getLatitude());
                lstBar.add(b);
                BarRepo.save(b);
            }
            return new ResponseEntity<>(BarRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    //Retourne les bars dans un rayon donné par distance en fonction de la latitude et longitude passée en parametre
    @RequestMapping(method = RequestMethod.GET, value ="/aroundme/{latitude}/{longitude}/{distance}")
    public ResponseEntity<?> getBarsAroundMe(@PathVariable Double latitude,
                                             @PathVariable Double longitude,
                                             @PathVariable Double distance)
    {

        // 8 rue de la grange #EPSI
        Double latBase = latitude;
        Double longBase = longitude;

        try {
            GooglePlaces client = new GooglePlaces("AIzaSyAm9JjC92Vg0ksWA1IEzbR944NpUczCc04");

            List<Place> places = client.getNearbyPlaces(latBase, longBase, distance, 20, Param.name("types").value("bar"));
            List<Bar> lstBar = new ArrayList<>();
            for (Place p : places)
            {
                Bar b = new Bar(p.getPlaceId(), p.getName(), p.getVicinity(),false,"happy_hours","open_hours", p.getLongitude(), p.getLatitude());
                lstBar.add(b);
                BarRepo.save(b);
            }
            return new ResponseEntity<>(BarRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
