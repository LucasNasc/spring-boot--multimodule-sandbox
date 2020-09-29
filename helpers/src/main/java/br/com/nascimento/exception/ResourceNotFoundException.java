package br.com.nascimento.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceNotFoundException extends RuntimeException {

    @JsonIgnore
    private HttpStatus httpStatusCode;

    private String code;

    private String message;

    private String description;

}
