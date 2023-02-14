package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "socio")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private long id;

    @Column
    private String dni;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @OneToOne(mappedBy = "socio", cascade = CascadeType.ALL)
    private Tarjeta tarjeta;
}
