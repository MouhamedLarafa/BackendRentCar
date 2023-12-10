package de.tekup.locationappb.services;


import de.tekup.locationappb.entites.Car;
import de.tekup.locationappb.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private CarRepository carRepository;

    public void deleteCar(int id){
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    public Car updateCar(Car car){
        Car car1 = getCarById(car.getId());

        car1.setBrand(car.getBrand());
        car1.setModel(car.getModel());
        car1.setSerie(car.getSerie());
        car1.setFuelType(car.getFuelType());
        car1.setGearType(car.getGearType());
        car1.setDayPrice(car.getDayPrice());
        car1.setImageUrl(car.getImageUrl());

        return carRepository.save(car1);
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCarss(){
        return carRepository.findAll();
    }

    public List<Car> getAllCars(LocalDate startDate , LocalDate endDate) {
        return carRepository.findAvailableCarsInDate(startDate,endDate);
    }
    public Car getCarById(int id){
        return carRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Car ID not Found"));
    }

    public List<Car> getCarByBrand(String brand){
        return carRepository.findByBrandContaining(brand);
    }




    //@PostConstruct
//    private void addVoituresToEmptyDB(){
//        if(carRepository.count()==0){
//            insertIntoDB(new Car("00Tunis0001",
//                    LocalDate.of(2022,01,03),"GOLF 7"));
//            insertIntoDB(new Car("00Tunis0002",
//                    LocalDate.of(2022,01,04),"POLO 8"));
//        }
//
//    }

}
