package br.com.nascimento;

import br.com.nascimento.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente findById(Long id){
       return repository
                .findById(id)
                .orElseThrow(
                        () -> ResourceNotFoundException.builder()
                                .httpStatusCode(HttpStatus.NOT_FOUND)
                                .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                                .description(String.format("Customer with id: %s not found! ", id))
                                .build());

    }

    @Override
    public void save(Cliente cliente){

        repository.save(cliente);

    }
}
