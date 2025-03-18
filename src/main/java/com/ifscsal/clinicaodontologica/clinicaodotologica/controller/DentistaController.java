package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.DentistaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaRepositery dentistaRepositery;

    @GetMapping
    public ResponseEntity getAresResponseEntity() {
        var dentistas = dentistaRepositery.findAll();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDentistaResponseEntity(@PathVariable int id) {
        var dentista = dentistaRepositery.findById(id);
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
        ArrayList<Dentista> dentistas = (ArrayList<Dentista>) dentistaRepositery.findAll();

        HashMap<String, Object> resposta = new HashMap<>();

        for (int c = 0; c < dentistas.size(); c++) {
            if(dentistas.get(c).getEmail().equalsIgnoreCase(dentista.email()) && dentistas.get(c).getSenha().equals(dentista.senha())) {
                resposta.put("Menssagem", "Logado no sistema!");
                return ResponseEntity.status(205).build();
            } else {
                resposta.put("Menssagem", "Senha/Email inválidos!");
                return ResponseEntity.status(404).build();
            }
        }

        resposta.put("Menssagem", "Insira sua senha ou e-mail valido!");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity alterarDentista(@RequestBody @Valid DentistaDTO dentista) {

        if(dentistaRepositery.findById(dentista.id()) == null) return ResponseEntity.status(404).build();

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
        if(dentistaRepositery.findById(id) == null) {
            return ResponseEntity.status(404).build();
        }
        dentistaRepositery.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
