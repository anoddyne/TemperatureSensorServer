package ru.anoddyne.temperaturesensorserver.services;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anoddyne.temperaturesensorserver.dto.SensorDTO;
import ru.anoddyne.temperaturesensorserver.models.Sensor;
import ru.anoddyne.temperaturesensorserver.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void addSensor(@Valid SensorDTO sensorDTO) {
        sensorRepository.save(convertToSensor(sensorDTO));
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public boolean existsByName(String name) {
        return sensorRepository.existsByName(name);
    }

    @Cacheable(value = "sensors", key = "#name")
    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
