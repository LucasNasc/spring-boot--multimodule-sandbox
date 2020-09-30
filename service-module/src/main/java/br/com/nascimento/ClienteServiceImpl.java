package br.com.nascimento;

import br.com.nascimento.exception.ResourceNotFoundException;
import br.com.nascimento.utils.BeanCopyNonNullProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;


    @Autowired
    BeanCopyNonNullProperties beanCopyNonNullProperties;

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
    public Page<Cliente> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Cliente update(Cliente cliente) {

        try {
            beanCopyNonNullProperties.copyProperties(cliente,findById(cliente.getId()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return repository.save(cliente);
    }

    @Override
    public void save(Cliente cliente){
        repository.save(cliente);
    }
}
