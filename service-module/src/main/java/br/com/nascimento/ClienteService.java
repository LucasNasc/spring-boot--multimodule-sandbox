package br.com.nascimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    Cliente findById(Long id);

    Page<Cliente> listAll(Pageable pageable);

    void delete(Long id);

    Cliente update(Cliente cliente);

    void save(Cliente cliente);
}
