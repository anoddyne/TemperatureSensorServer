package ru.anoddyne.temperaturesensorserver.validation;


import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.anoddyne.temperaturesensorserver.dto.SensorDTO;
import ru.anoddyne.temperaturesensorserver.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorService.existsByName(sensorDTO.getName())) {
            errors.rejectValue("name", "name.already.exists", "Sensor with this name already exists");
        }

    }
}
