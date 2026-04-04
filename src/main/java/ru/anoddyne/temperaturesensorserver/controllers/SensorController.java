package ru.anoddyne.temperaturesensorserver.controllers;

import jakarta.validation.Valid;
import ru.anoddyne.temperaturesensorserver.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anoddyne.temperaturesensorserver.dto.SensorDTO;
import ru.anoddyne.temperaturesensorserver.services.SensorService;
import ru.anoddyne.temperaturesensorserver.validation.SensorValidator;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult);

        sensorService.addSensor(sensorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
