package br.com.nascimento;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
