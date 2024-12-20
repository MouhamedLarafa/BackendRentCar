package de.tekup.locationappb.repositories;


import de.tekup.locationappb.entites.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findByBrandContaining(String brand);

    @Query(value = "SELECT * FROM car where id not in " +
            "( SELECT car_id from location where location.end_date > ':startDate' " +
            "OR location.start_date BETWEEN ':startDate'AND':endDate')",nativeQuery = true)
    List<Car> findAvailableCarsInDate(@Param("startDate")LocalDate startDate,
                                      @Param("endDate")LocalDate endDate);
}
