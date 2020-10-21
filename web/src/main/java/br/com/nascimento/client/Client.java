package br.com.nascimento.client;

import br.com.nascimento.Cliente;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1")
                .basicAuthentication("vingadork", "master90").build();

        Cliente cliente = restTemplate.getForObject("/cliente/{id}", Cliente.class, 3);
        ResponseEntity<Cliente> entity = restTemplate.getForEntity("/cliente/{id}", Cliente.class, 3);

        Cliente[] entityList = restTemplate.getForObject("/cliente", Cliente[].class);

        System.out.println(cliente);
        System.out.println(entity);
        System.out.println(Arrays.toString(entityList));

    }
}
