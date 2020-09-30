package br.com.nascimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends AbstractEntity {

    @NotEmpty(message = "O Campo name e obrigatorio ")
    private String name;

    private Integer idade;
}
