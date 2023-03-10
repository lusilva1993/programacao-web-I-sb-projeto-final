package br.com.ada.projetoFinal.config;

import br.com.ada.projetoFinal.model.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PWIConfiguration {

    @Bean
    public Cliente getCliente() {
        return new Cliente();
    }

}
