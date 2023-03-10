package br.com.ada.projetoFinal.service;

import br.com.ada.projetoFinal.model.Cliente;
import br.com.ada.projetoFinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Page<Cliente> listarPaginado(Integer numPagina, Integer tamPagina) {
        return this.clienteRepository
                .findAll(PageRequest.of(numPagina, tamPagina, Sort.by(Sort.Order.asc("id"))));
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarClientePeloDocumento(String cpfCnpj) {
        return this.clienteRepository.findByCpfCnpjContaining(cpfCnpj);
    }

    public void removerClientePorId(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
