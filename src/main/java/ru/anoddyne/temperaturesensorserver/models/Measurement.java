package ru.anoddyne.temperaturesensorserver.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="value")
    @NotNull
    private Double value;

    @Column(name="is_raining")
    @NotNull
    private Boolean isRaining;

    @Column(name="measurement_time")
    @NotNull
    private LocalDateTime measurementTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}