package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import java.util.Collections;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.DentistaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DentistaControllerTest {

    @InjectMocks
    DentistaController dentistaController;

    @Mock
    DentistaRepositery dentistaRepositery;

    @Autowired
    private MockMvc mockMvc;
    DentistaDTO dentista;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dentistaController).build();
        this.dentista = new DentistaDTO(1,
                "Luiz Felipe",
                "Arrancar dente",
                "luiz@luiz.com",
                "123456",
                "CR444");
    }


    @Test
    void deveCadastrarDentistaComSucesso() throws Exception{
        Dentista newDentista = new Dentista.DentistaBuilder()
                .cro(dentista.cro())
                .nome(dentista.nome())
                .email(dentista.email())
                .especialidade(dentista.especialidade())
                .senha(dentista.senha())
                .build();

        when(dentistaRepositery.save(newDentista)).thenReturn(newDentista);

        mockMvc.perform(post("/dentista/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dentista)))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarDentistaPorIdComSucesso() throws Exception{
        when(dentistaRepositery.findById(dentista.id()))
                .thenReturn(Optional.of(new Dentista(dentista)));

        Optional<Dentista> result = dentistaRepositery.findById(dentista.id());

        assert result.isPresent();

    }

    @Test
    void deveListarTodosOsDentistaComSucesso() throws Exception {
        when(dentistaRepositery.findAll())
                .thenReturn(Collections.emptyList());

        List<Dentista> result = dentistaRepositery.findAll();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    void deveDeletarUmDentistaComSucesso() throws Exception {
        when(dentistaRepositery.findByIdAndAtivoTrue(dentista.id()))
                .thenReturn(Optional.of(new Dentista(dentista)));

        when(dentistaRepositery.save(any(Dentista.class))).thenReturn(new Dentista(dentista));
        ResponseEntity responseEntity = dentistaController.deletarDentista(dentista.id());
        verify(dentistaRepositery, times(1)).save(any(Dentista.class));
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}