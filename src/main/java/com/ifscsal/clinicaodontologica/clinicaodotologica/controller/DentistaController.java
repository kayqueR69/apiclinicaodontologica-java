package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.DentistaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaRepositery dentistaRepositery;

    @GetMapping
    public ResponseEntity buscarTodosOsDentistas() {
        var dentistas = dentistaRepositery.findAllByAtivoTrue();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarDentistaPorId(@PathVariable int id) {
        var dentista = dentistaRepositery.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(dentista);
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarDentista(@RequestBody @Valid DentistaDTO dentista) {
        Dentista newDentista = new Dentista.DentistaBuilder()
                .cro(dentista.cro())
                .nome(dentista.nome())
                .email(dentista.email())
                .especialidade(dentista.especialidade())
                .senha(dentista.senha())
                .build();
        dentistaRepositery.save(newDentista);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DentistaDTO dentista) {
        Optional<Dentista> dentistas = dentistaRepositery.findByEmailAndAtivoTrue(dentista.email());

        HashMap<String, Object> resposta = new HashMap<>();

        if(dentistas.isPresent()) {
            Dentista dentistaLogado = dentistas.get();
            if(dentistaLogado.getSenha().equals(dentista.senha())) {
                resposta.put("Menssagem", "Logado no sistema!");
                resposta.put("Dentista", dentistaLogado);
                return ResponseEntity.ok().body(resposta);
            } else {
                resposta.put("Menssagem", "Login ou senha invalido!");
                return ResponseEntity.status(401).body(resposta);
            }
        }

        resposta.put("Menssagem", "Insira senha ou e-mail valido!");
        return ResponseEntity.status(404).body(resposta);
    }

    @PutMapping
    public ResponseEntity alterarDentista(@RequestBody @Valid DentistaDTO dentista) {

        if(dentistaRepositery.findByIdAndAtivoTrue(dentista.id()).isEmpty()) return ResponseEntity.status(404).build();

        Dentista dentistaAlterado = dentistaRepositery.getReferenceById(dentista.id());

        if(dentista.nome() != null) {
            dentistaAlterado.setNome(dentista.nome());
        }
        if(dentista.especialidade() != null) {
            dentistaAlterado.setEspecialidade(dentista.especialidade());
        }
        if(dentista.senha() != null) {
            dentistaAlterado.setSenha(dentista.senha());
        }
        if(dentista.email() != null) {
            dentistaAlterado.setEmail(dentista.email());
        }
        if(dentista.cro() != null) {
            dentistaAlterado.setCro(dentista.cro());
        }

        dentistaRepositery.save(dentistaAlterado);
        return ResponseEntity.status(205).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDentista(@PathVariable int id) {
        Optional<Dentista> dentista = dentistaRepositery.findByIdAndAtivoTrue(id);
        if(dentista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Dentista dentistaDelete = dentista.get();
        dentistaDelete.setAtivo(false);
        dentistaRepositery.save(dentistaDelete);

        return ResponseEntity.noContent().build();
    }
}
