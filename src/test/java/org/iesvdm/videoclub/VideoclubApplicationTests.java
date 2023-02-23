package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private IdiomaRepository idiomaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void contextLoads() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        peliculas.forEach(System.out :: println);
    }

}
