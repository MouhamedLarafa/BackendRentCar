package de.tekup.locationappb;


import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.entites.Location;
import de.tekup.locationappb.entites.User;
import de.tekup.locationappb.repositories.CarRepository;
import de.tekup.locationappb.repositories.LocationRepository;
import de.tekup.locationappb.repositories.UserRepository;
import de.tekup.locationappb.services.EmailSenderService;
import de.tekup.locationappb.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class LocationServicesTests {

    @Mock
    LocationRepository locationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    EmailSenderService emailSenderService;

    User user = User.builder().username("hama").build();
    Car car = Car.builder().brand("porsche").build();

    Location location = Location.builder().status("test").startDate(LocalDate.now())
            .endDate(LocalDate.now().plusDays(5)).user(user).car(car).build();




    @InjectMocks
    LocationService locationService;

    @Test
    void addLocation(){
        Mockito.when(locationRepository.save(Mockito.any(Location.class))).then( a -> {
            Location l = a.getArgument(0,Location.class);
            l.setId(1);
            return l;
        });
        log.info("Before :" + location.getId());
        Location l = locationService.addLocation(location);
        Assertions.assertSame(l,location);
        log.info("After :" + location.getId());
        Mockito.verify(locationRepository).save(location);

    }


    @Test
    void getlocationsbyClient(){
        Mockito.when(locationRepository.findLocationsByUserUsername(Mockito.any(String.class))).then( a -> {
            List<Location> l = new ArrayList<>();
            return l;
        });
        List<Location> locations = locationService.getlocationsbyClient("test");

        Mockito.verify(locationRepository).findLocationsByUserUsername(Mockito.any());

    }


}
