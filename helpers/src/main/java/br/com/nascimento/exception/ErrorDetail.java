package br.com.nascimento.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "httpStatusCode", "code", "developerMessage", "description", "field" })
public class ErrorDetail {
    private String title;

    private HttpStatus httpStatusCode;

    private String code;

    private String developerMessage;

    private String timestamp;

    private String description;

    private String field;

}
