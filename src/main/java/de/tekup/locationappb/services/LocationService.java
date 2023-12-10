package de.tekup.locationappb.services;

import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.entites.Location;
import de.tekup.locationappb.entites.User;
import de.tekup.locationappb.repositories.CarRepository;
import de.tekup.locationappb.repositories.LocationRepository;
import de.tekup.locationappb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private UserRepository userRepository;
    private CarRepository carRepository;
    private EmailSenderService emailSenderService;

    public Location addLocation(Location location){
        User user = userRepository.findById(location.getUser().getUsername()).get();

        Car car1 = carRepository.findById(location.getCar().getId()).get();

        location.setUser(user);
        location.setCar(car1);
        //calculate price
        location.setPrice(calculatePrice(location.getStartDate(), location.getEndDate(), location.getCar()));
        location.setStatus("In progress");
        try {
            emailSenderService.sendmail(user.getUsername());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return locationRepository.save(location);
    }

    //calculate price
    private double calculatePrice(LocalDate startDate, LocalDate endDate, Car car){
        long nbDays = ChronoUnit.DAYS.between(startDate,endDate)+1;
        return  car.getDayPrice() * nbDays;
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public Location updateSatuts(Location location){
        Location location1 = locationRepository.findById(location.getId()).get();
        if(location1 != null){
            location1.setStatus(location.getStatus());
            return locationRepository.save(location1);
        }
        return null;
    }
    public List<Location> getlocationsbyClient(String id){
        return locationRepository.findLocationsByUserUsername(id);
    }

}
