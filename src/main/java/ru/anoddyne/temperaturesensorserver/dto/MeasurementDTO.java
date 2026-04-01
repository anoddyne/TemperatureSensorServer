package ru.anoddyne.temperaturesensorserver.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {
    @NotNull(message = "Значение температуры не должно быть пустым!")
    @DecimalMin(value = "-100.0", message = "Значение температуры не должно быть меньше -100.0 градусов!")
    @DecimalMax(value = "100.0", message = "Значение температуры не должно быть больше 100.0 градусов!")
    private Double value;

    @NotNull(message = "Необходимо указать, есть ли дождь сейчас или нет!")
    private Boolean isRaining;

    @NotNull
    private SensorDTO sensor;
}
