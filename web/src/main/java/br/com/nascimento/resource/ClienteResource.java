package br.com.nascimento.resource;

import br.com.nascimento.Cliente;
import br.com.nascimento.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("cliente")
public class ClienteResource {

    @Autowired
    ClienteService service;

//    @GetMapping()
//    public ResponseEntity<List<Cliente>> listAll(){
//        return ResponseEntity.ok(repository.findAll());
//    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE
    }, path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente){
        service.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<?>  delete(@PathVariable("id") Long id){
//        repository.delete(repository.findById(id).get());
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity<Customer>  update(@PathVariable("id") Long id,
//                                            @RequestBody Customer customer){
//        Customer updatedCustomer = repository.findById(id).get();
//        updatedCustomer.setName(customer.getName());
//        return ResponseEntity.ok(repository.save(updatedCustomer));
//    }
}
