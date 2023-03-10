package br.com.ada.projetoFinal.controller;

import br.com.ada.projetoFinal.ClienteDTO;
import br.com.ada.projetoFinal.model.Cliente;
import br.com.ada.projetoFinal.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/") //POST
    public ResponseEntity<String> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente clienteDB = Cliente.builder()
                    .cpfCnpj(clienteDTO.getCpfCnpj())
                    .nome(clienteDTO.getNome())
                    .dataNascimento(clienteDTO.getDataNascimento())
                    .endereco(clienteDTO.getEndereco())
                    .build();
            this.clienteService.createCliente(clienteDB);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Cliente cadastrado!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/todos")
    public List<Cliente> listarTodos() {
        return this.clienteService.listarTodos();
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(id);
        if (optionalCliente.isPresent()) {
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<String> atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        //1 - buscar cliente
        //2 - atualizar

        try {
            Optional<Cliente> optionalCliente = this.clienteService.buscarClientePeloDocumento(clienteDTO.getCpfCnpj());

            if (optionalCliente.isPresent()) {
                Cliente clientePorDocumentoDB = optionalCliente.get();
                Cliente clienteAtualizar = Cliente.builder()
                        .cpfCnpj(clienteDTO.getCpfCnpj())
                        .nome(clienteDTO.getNome())
                        .dataNascimento(clienteDTO.getDataNascimento())
                        .endereco(clienteDTO.getEndereco())
                        .build();
                this.clienteService.createCliente(clienteAtualizar);

                return ResponseEntity
                        .ok("Cliente atualizado!");
            }
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable Long id){
        this.clienteService.removerClientePorId(id);
    }
}
