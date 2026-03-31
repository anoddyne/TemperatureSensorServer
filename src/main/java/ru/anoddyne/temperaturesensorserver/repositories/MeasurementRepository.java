package ru.anoddyne.temperaturesensorserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.anoddyne.temperaturesensorserver.models.Measurement;

@EnableJpaRepositories
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    long countAllByIsRainingTrue();
}
