package br.com.ada.projetoFinal.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_cliente")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpfCnpj;

    private String nome;
    private String endereco;
    private String telefone;

}
