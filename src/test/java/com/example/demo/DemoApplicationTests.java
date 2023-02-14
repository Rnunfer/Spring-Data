package com.example.demo;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Socio;
import com.example.demo.domain.Tarjeta;
import com.example.demo.domain.Tutorial;
import com.example.demo.repository.SocioRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.repository.TutorialRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;

    /*@Autowired
    SocioRepository socioRepository;*/

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

}
