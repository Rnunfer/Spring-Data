package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repository.PeliculaRepository;
import com.example.demo.repository.SocioRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.repository.TutorialRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testTutorialRepository() {

        Tutorial tutorial1 = Tutorial.builder().title("Tutorial JPA")
                .description("Se describen aspectos de modelo/entidad con JPA/Hibernate")
                .build();

        tutorialRepository.save(tutorial1);

        List<Tutorial> tutorialList = tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);
    }

    @Test
    void testTutorialWithCommentsRepository() {

        Comment comment1 = Comment.builder().content("El tutorial no está mal, pero hay cosillas que no cuenta").build();
        Comment comment2 = Comment.builder().content("Genial!").build();

        List<Comment> commentList = Arrays.asList(comment1, comment2);

        //Anotacion Lombok @Builder
        Tutorial tutorial1 = Tutorial.builder().title("Tuto2 JPA")
                .description("Otro tuto sobre modelo/entidad con JPA/Hibernate")
                .build();

        Tutorial tutorialSave = tutorialRepository.save(tutorial1);
        //Alternativa tutorialSave


        //Alternativa tutorialFnd recargando la entidad para tener la colección Comments creada
        //Tutorial tutorialFind = tutorialRepository.findById(tutorialSave.getId()).get();

        //Seteamos el tutorial
        commentList.forEach( c -> c.setTutorial(tutorialSave));
        //commentList.forEach( c -> c.setTutorial(tutorialFind));

        tutorialSave.setComments(commentList);
        //tutorialFind.getComments().addAll(commentList);

        // Segunda actualización para actualizar las FK id_tutorial de comentarios nuevos.

        tutorialRepository.save(tutorialSave);
        //tutorialRepository.save(tutorialFind);

        List<Tutorial> tutorialList = tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);

    }

    @Test
    @Order(1)
    void testSocioTarjeta() {

        Tarjeta tarjeta = Tarjeta.builder()
                .numero("5837873248732687")
                .caducidad("04/27")
                .build();

        Socio socio = Socio.builder()
                .dni("58384964G")
                .nombre("Luis")
                .apellidos(" Luinardo de León")
                .tarjeta(tarjeta)
                .build();

        tarjeta.setSocio(socio);
        socioRepository.save(socio);

        List<Socio> socioList = socioRepository.findAll();
    }

    @Test
    void testPeliculaCategoriaManyToMany() {

        Pelicula pelicula = Pelicula.builder()
                .anyoLanzamiento("2005")
                .caracteristicasEspecialesStr("Trailers")
                .clasificacion(Clasificacion.valueOf("PG"))
                .costeReemplazo(BigDecimal.valueOf(25))
                .descripcion("Aventuras en el año 2005")
                .duracion(Duration.ofDays(90))
                .idioma("Español")
                .idiomaOriginal("Español")
                .periodoAlquiler(Period.ofYears(3))
                .precioAlquiler(BigDecimal.valueOf(5))
                .titulo("2005")
                .build();

        Categoria categoria = Categoria.builder()
                .nombre("Aventura")
                .build();

        // Falta conectar la pelicula y categoria

        peliculaRepository.save(pelicula);

        List<Pelicula> peliculaList = peliculaRepository.findAll();
    }

}
