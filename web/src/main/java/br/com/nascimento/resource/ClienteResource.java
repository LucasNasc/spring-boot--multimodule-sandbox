package br.com.nascimento.resource;

import br.com.nascimento.Cliente;
import br.com.nascimento.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("v1")
public class ClienteResource {

    @Autowired
    ClienteService service;


    @GetMapping()
    public ResponseEntity<?> listAll(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails)
    {
        System.out.println(userDetails);
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE
    }, path = "/cliente/{id}")
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

    @DeleteMapping(path = "/admin/cliente/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/admin/cliente/{id}")
    public ResponseEntity<?>  update(@PathVariable("id") Long id,
                                            @RequestBody Cliente cliente){
        cliente.setId(id);
        Cliente client = service.update(cliente);
        client.add(linkTo(methodOn(ClienteResource.class).findById(id)).withSelfRel().withType(HttpMethod.PUT.name()));
        return ResponseEntity.ok(client);
    }
}
