package de.tekup.locationappb.repositories;

import de.tekup.locationappb.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findLocationsByUserUsername(String client);
}
