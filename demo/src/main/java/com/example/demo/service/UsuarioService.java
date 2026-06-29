package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario salvar(Usuario u) {
        validarEmailDuplicado(u.getEmail(), null);
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return repository.save(u);
    }

    public Usuario atualizar(Long id, Usuario u) {
        Usuario antigo = buscar(id);

        if (u.getEmail() != null && !u.getEmail().equalsIgnoreCase(antigo.getEmail())) {
            validarEmailDuplicado(u.getEmail(), id);
        }

        antigo.setNome(u.getNome());
        antigo.setEmail(u.getEmail());

        if (u.getSenha() != null && !u.getSenha().isBlank()) {
            antigo.setSenha(passwordEncoder.encode(u.getSenha()));
        }

        return repository.save(antigo);
    }

    public void deletar(Long id) {
        repository.delete(buscar(id));
    }

    private void validarEmailDuplicado(String email, Long idIgnorado) {
        if (email == null || email.isBlank()) {
            return;
        }

        boolean existe = repository.existsByEmail(email);
        if (idIgnorado != null) {
            Usuario usuarioExistente = repository.findById(idIgnorado).orElse(null);
            if (usuarioExistente != null && email.equalsIgnoreCase(usuarioExistente.getEmail())) {
                existe = false;
            }
        }

        if (existe) {
            throw new RuntimeException("Email já cadastrado");
        }
    }
}
