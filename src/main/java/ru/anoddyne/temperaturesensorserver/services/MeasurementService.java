package ru.anoddyne.temperaturesensorserver.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anoddyne.temperaturesensorserver.dto.MeasurementDTO;
import ru.anoddyne.temperaturesensorserver.exception.SensorNotFoundException;
import ru.anoddyne.temperaturesensorserver.models.Measurement;
import ru.anoddyne.temperaturesensorserver.models.Sensor;
import ru.anoddyne.temperaturesensorserver.repositories.MeasurementRepository;
import ru.anoddyne.temperaturesensorserver.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementRepository.findAll().stream().map(this::convertToMeasurementDTO).toList();
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public long getCountOfRainyDays() {
        return measurementRepository.countAllByIsRainingTrue();
    }

    @Transactional
    public void addMeasurement(MeasurementDTO measurementDTO) {
        Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName()).orElseThrow(SensorNotFoundException::new);
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(sensor);
        measurement.setMeasurementTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    @Transactional
    public void addMeasurementsBatch(List<MeasurementDTO> measurementsDTOList) {
        LocalDateTime time = LocalDateTime.now();

        List<Measurement> measurements = measurementsDTOList.stream().map(dto -> {
            Sensor sensor = sensorService.findByName(dto.getSensor().getName()).orElseThrow(SensorNotFoundException::new);
            Measurement entity = convertToMeasurement(dto);
            entity.setSensor(sensor);
            entity.setMeasurementTime(time);
            return entity;
            }).toList();

        measurementRepository.saveAll(measurements);
    }

}
