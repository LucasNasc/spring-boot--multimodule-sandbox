package br.com.nascimento.client;

import br.com.nascimento.Cliente;
import br.com.nascimento.PageableResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Client {

    public static void main(String[] args) {
        RestTemplate restTemplate = getTemplateWithAuthentication(
                                    "http://localhost:8080/v1",
                                    "vingadork",
                                    "master90");

        ResponseEntity<Cliente> entity = findByIdClient(restTemplate, 4L);
        ResponseEntity<PageableResponse<Cliente>> entityList = restTemplate.exchange("/cliente",
                HttpMethod.GET,null,
                new ParameterizedTypeReference<PageableResponse<Cliente>>() {});

        System.out.println(entity);
        System.out.println(entityList.getBody());

    }

    private static RestTemplate getTemplateWithAuthentication(String uri, String username, String password) {
        return new RestTemplateBuilder()
                .rootUri(uri)
                .basicAuthentication(username, password).build();
    }

    private static ResponseEntity<Cliente> findByIdClient(RestTemplate restTemplate, Long id) {
        return restTemplate.getForEntity("/cliente/{id}", Cliente.class, id);
    }

}
