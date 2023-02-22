package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repository.PeliculaRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.SocioRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.repository.TutorialRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    PersonaRepository personaRepository;

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

        Pelicula pelicula = Pelicula.builder().titulo("Indiana Jones")
                .descripcion("Película para toda la familia de aventura")
                .anyoLanzamiento("1990")
                .idioma("Español")
                .idiomaOriginal("Inglés")
                .duracion(Duration.parse("PT1H40M"))
                .precioAlquiler(new BigDecimal("20.50"))
                .periodoAlquiler(Period.of(0,1,15))
                .clasificacion(Clasificacion.R)
                .caracteristicasEspecialesStr("Trailers,Commentaries")
                .categoria(new HashSet<>())
                .ultimaModificacion(new Date())
                .build();

        Categoria categoria = Categoria.builder()
                .nombre("Aventura")
                .peliculas(new HashSet<>())
                .ultimaModificacion(new Date())
                .build();

        Set<Categoria> listaCategorias = new HashSet<>();
        listaCategorias.add(categoria);
        Set<Pelicula> listaPeliculas = new HashSet<>();
        listaPeliculas.add(pelicula);

        pelicula.setCategoria(listaCategorias);
        categoria.setPeliculas(listaPeliculas);

        peliculaRepository.save(pelicula);

        List<Pelicula> peliculaList = peliculaRepository.findAll();
    }

    @Test
    public void personElementCollectionStringAndAddressEmbeddable() {
        Persona persona = Persona.builder().name("Pablo Mermol")
                .phoneNumbers(new HashSet<>())
                .addresses((new HashSet<>()))
                .build();

        Address mainAddress = Address.builder().houseNumber(30)
                .street("Bélgica")
                .city("Málaga")
                .zipCode(29402)
                .build();

        Address address1 = Address.builder().houseNumber(23)
                .street("Portugal")
                .city("Málaga")
                .zipCode(29482)
                .build();

        Address address2 = Address.builder().houseNumber(23)
                .street(("Francia"))
                .city("Málaga")
                .zipCode(29403)
                .build();

        persona.setMainAddress(mainAddress);
        persona.getAddresses().add(address1);
        persona.getAddresses().add(address2);
        persona.getPhoneNumbers().add("952132439");
        persona.getPhoneNumbers().add("951234567");
        personaRepository.save(persona);
        Persona personaSaved = personaRepository.findById(persona.getId()).get();
    }

}
