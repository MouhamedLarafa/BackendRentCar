package de.tekup.locationappb.controllers;

import de.tekup.locationappb.entites.Location;
import de.tekup.locationappb.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class LocationController {

    private LocationService locationService;

    @PostMapping("/addLocation")
    public Location locationAdd(@RequestBody Location location){
        return locationService.addLocation(location);
    }

    @PatchMapping("/updatestatus")
    public Location statusUpdate(@RequestBody Location location){
        return locationService.updateSatuts(location);
    }

    @GetMapping("/allLocations")
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }
    @GetMapping(value = "/UserLocations/{id}" )
    public List<Location> getLocations(@PathVariable("id")String id){
        return locationService.getlocationsbyClient(id);
    }
}
