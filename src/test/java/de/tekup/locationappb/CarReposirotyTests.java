package de.tekup.locationappb;

import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.repositories.CarRepository;
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
public class CarReposirotyTests {
    @Autowired
    CarRepository carRepository;

    static Car car = Car.builder().brand("test").model("test").build();

    @Test
    @Order(1)
    public void ajouterCar(){
        car = carRepository.save(car);
        log.info("id"  + car.getId());
        log.info("ajout ==>" + car.toString());
        Assertions.assertNotNull(car.getId());
        Assertions.assertNotEquals(0,car.getId());
    }

    @Test
    @Order(2)
    public void modifierCar(){
        car.setBrand("test1");
        car = carRepository.save(car);
        log.info("modifier : " + car);
        Assertions.assertNotEquals(car.getBrand(),"test");
        Assertions.assertSame(car.getBrand(),"test1");
    }

    @Test
    @Order(3)
    public void trouverCar(){
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        log.info("List voiture : " + cars);
        Assertions.assertTrue(cars.size()>0);
    }

    @Test
    @Order(4)
    public void chercherCar(){
        Car c = carRepository.findById(car.getId()).get();
        Assertions.assertEquals(c.getId(),car.getId());
    }

    @Test
    @Order(5)
    public void supprimerCar(){
        carRepository.deleteById(car.getId());
        Car c = carRepository.findById(car.getId()).orElse(null);
        Assertions.assertNull(c);
    }
}
