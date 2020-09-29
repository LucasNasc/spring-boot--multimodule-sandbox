package br.com.nascimento;

public interface ClienteService {

    Cliente findById(Long id);

    void save(Cliente cliente);
}
