package ru.anoddyne.temperaturesensorserver.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.anoddyne.temperaturesensorserver.dto.MeasurementDTO;
import ru.anoddyne.temperaturesensorserver.exception.ValidationException;
import ru.anoddyne.temperaturesensorserver.services.MeasurementService;
import ru.anoddyne.temperaturesensorserver.validation.MeasurementValidator;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add/batch")
    public ResponseEntity<HttpStatus> addMeasurementBatch(@RequestBody List<@Valid MeasurementDTO> measurementDTOS, BindingResult bindingResult) {
        measurementDTOS.forEach(dto -> measurementValidator.validate(dto, bindingResult));
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult);
        measurementService.addMeasurementsBatch(measurementDTOS);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<List<MeasurementDTO>> getMeasurements() {
        return ResponseEntity.status(HttpStatus.OK).body(measurementService.getAllMeasurements());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getCountOfRainyDays() {
        return ResponseEntity.status(HttpStatus.OK).body(measurementService.getCountOfRainyDays());
    }

}
