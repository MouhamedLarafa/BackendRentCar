package de.tekup.locationappb.entites;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private double price;

    @Column
    private String status;
    @Column
    @CreationTimestamp
    private LocalDate createdLocationDate;

    @Column
    @UpdateTimestamp
    private LocalDate updatedLocationDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;



//    @PostLoad
//    private void calculatePrice(){
//        long nbDays = ChronoUnit.DAYS.between(startDate,endDate)+1;
//        price = car.getDayPrice() * nbDays;
//    }
}
