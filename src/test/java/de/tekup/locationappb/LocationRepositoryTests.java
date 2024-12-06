package de.tekup.locationappb;
import de.tekup.locationappb.entites.Location;
import de.tekup.locationappb.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.class)
public class LocationRepositoryTests {

    @Autowired
    LocationRepository locationRepository;

    static Location location = Location.builder().status("test").build();

    @Test
    @Order(1)
    public void ajouterLocation(){
        location = locationRepository.save(location);
        log.info("id"  + location.getId());
        log.info("ajout ==>" + location.toString());
        Assertions.assertNotNull(location.getId());
        Assertions.assertNotEquals(0,location.getId());
    }

    @Test
    @Order(2)
    public void modifierLocation(){
        location.setStatus("test1");
        location = locationRepository.save(location);
        log.info("modifier : " + location);
        Assertions.assertNotEquals(location.getStatus(),"test");
        Assertions.assertSame(location.getStatus(),"test1");
    }

    @Test
    @Order(3)
    public void trouverLocation(){
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        log.info("List voiture : " + locations);
        Assertions.assertTrue(locations.size()>0);
    }

    @Test
    @Order(4)
    public void chercherLocation(){
        Location l = locationRepository.findById(location.getId()).get();
        Assertions.assertEquals(l.getId(),location.getId());
    }

    @Test
    @Order(5)
    public void supprimerLocation(){
        locationRepository.deleteById(location.getId());
        Location l = locationRepository.findById(location.getId()).orElse(null);
        Assertions.assertNull(l);

    }
}
