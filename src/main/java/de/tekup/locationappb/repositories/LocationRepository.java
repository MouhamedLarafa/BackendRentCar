package de.tekup.locationappb.repositories;

import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findLocationsByUserUsername(String client); //containing =  like %'k'%
}
