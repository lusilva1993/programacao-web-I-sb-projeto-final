package br.com.ada.projetoFinal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {

    @Column(unique = true)
    @NotBlank(message = "Campo não pode estar vazio!")
    private String cpfCnpj;

    @NotBlank(message = "Campo não pode estar vazio!")
    private String nome;

    @NotBlank(message = "Campo não pode estar vazio!")
    private String endereco;

    @NotBlank(message = "Campo não pode estar vazio!")
    private String telefone;
}
