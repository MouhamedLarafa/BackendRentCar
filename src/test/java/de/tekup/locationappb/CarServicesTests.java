package de.tekup.locationappb;

import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.repositories.CarRepository;
import de.tekup.locationappb.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CarServicesTests {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    public void getCarByBrand(){
        Mockito.when(carRepository.findByBrandContaining(Mockito.anyString())).then(a -> {
            List<Car> cars = new ArrayList<>();
            return cars;
        });
        List<Car> cars = carService.getCarByBrand("test");
        Mockito.verify(carRepository).findByBrandContaining(Mockito.anyString());
    }
}
