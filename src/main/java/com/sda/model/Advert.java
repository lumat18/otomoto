package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@Entity(name = "adverts")
@NoArgsConstructor
@AllArgsConstructor
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Car car;
    @ManyToOne(targetEntity = User.class)
    private User author;
    private String description;
    private Integer price;
    private LocalDate date;

    @ManyToMany(mappedBy = "observed", fetch = FetchType.EAGER)
    private List<User> observers;
}
