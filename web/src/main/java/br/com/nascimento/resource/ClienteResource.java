package br.com.nascimento.resource;

import br.com.nascimento.Cliente;
import br.com.nascimento.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Affordance;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;


@RestController
@RequestMapping("cliente")
public class ClienteResource {

    @Autowired
    ClienteService service;

    @GetMapping()
    public ResponseEntity<?> listAll(Pageable pageable){
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE
    }, path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Cliente cliente = service.findById(id);
        cliente.add(linkTo(methodOn(ClienteResource.class).findById(id)).withSelfRel().withType(HttpMethod.GET.name()));
        return ResponseEntity.ok(cliente);
    }


    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente){
        service.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable("id") Long id,
                                            @RequestBody Cliente cliente){
        cliente.setId(id);
        Cliente client = service.update(cliente);
        client.add(linkTo(methodOn(ClienteResource.class).findById(id)).withSelfRel().withType(HttpMethod.PUT.name()));
        return ResponseEntity.ok(client);
    }
}
