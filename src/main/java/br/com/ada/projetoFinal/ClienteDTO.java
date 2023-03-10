package br.com.ada.projetoFinal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {

    private String cpfCnpj;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
}
