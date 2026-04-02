package ru.anoddyne.temperaturesensorserver.validation;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.anoddyne.temperaturesensorserver.dto.MeasurementDTO;
import ru.anoddyne.temperaturesensorserver.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

   private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (measurementDTO.getSensor() == null) {
            return;
        }

        String sensorName = measurementDTO.getSensor().getName();

        if (sensorName == null || sensorName.isBlank()) {
            errors.rejectValue("name", "sensor.name.empty", "Sensor name must not be empty");
        } else if (!sensorService.existsByName(sensorName)) {
            errors.rejectValue("sensor", "sensor.not.exists", "Sensor with this name is not registered");
        }
    }
}
