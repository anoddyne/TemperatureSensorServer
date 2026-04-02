package ru.anoddyne.temperaturesensorserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer status;
    private long timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Map<String, String>> errors;
}
