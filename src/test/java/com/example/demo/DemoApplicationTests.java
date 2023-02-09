package com.example.demo;

import domain.Tutorial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repository.TutorialRepository;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

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

}
