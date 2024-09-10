package com.prueba.tecnica.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.models.Comic;
import com.prueba.tecnica.service.MarvelAPIClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarvelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarvelAPIClient marvelAPIClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void obtenerComicsTest() throws Exception {
        // Creación de objetos Comic
        Comic comic1 = new Comic();
        comic1.setId(82967L);
        comic1.setTitle("Marvel Previews (2017)");
        comic1.setDescription("");
        comic1.setModified(new Date());
        comic1.setUrlImagen("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg");

        Comic comic2 = new Comic();
        comic2.setId(82968L);
        comic2.setTitle("Spider-Man: The Spider War");
        comic2.setDescription("A great storyline involving Spider-Man.");
        comic2.setModified(new Date());
        comic2.setUrlImagen("http://example.com/spider-man.jpg");

        List<Comic> comics = new ArrayList<>();
        comics.add(comic1);
        comics.add(comic2);

        // Mockeando el cliente MarvelAPIClient
        when(marvelAPIClient.obtenerListaComics()).thenReturn(comics);

        // Ejecutando la solicitud y verificando la respuesta
        MvcResult result = mockMvc.perform(get("/api/marvel/comics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Obteniendo la respuesta como cadena JSON
        String content = result.getResponse().getContentAsString();
        System.out.println("Respuesta JSON: " + content);

        // Verificar los valores esperados usando jsonPath
        mockMvc.perform(get("/api/marvel/comics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(82967))
                .andExpect(jsonPath("$[0].title").value("Marvel Previews (2017)"))
                .andExpect(jsonPath("$[0].description").value(""))
                .andExpect(jsonPath("$[0].urlImagen").value("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"))
                .andExpect(jsonPath("$[1].id").value(82968))
                .andExpect(jsonPath("$[1].title").value("Spider-Man: The Spider War"))
                .andExpect(jsonPath("$[1].description").value("A great storyline involving Spider-Man."))
                .andExpect(jsonPath("$[1].urlImagen").value("http://example.com/spider-man.jpg"));
    }
}
