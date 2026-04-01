package ru.anoddyne.temperaturesensorserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {
    @Size(min = 3, max = 30, message = "Название сенсора должно быть от 3 до 30 символов!")
    @NotBlank(message = "Название сенсора не должно быть пустым!")
    private String name;
}
