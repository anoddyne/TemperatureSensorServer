package ru.anoddyne.temperaturesensorserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.anoddyne.temperaturesensorserver.models.Sensor;

import java.util.Optional;

@EnableJpaRepositories
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    boolean existsByName(String name);
    Optional<Sensor> findByName(String name);
}
