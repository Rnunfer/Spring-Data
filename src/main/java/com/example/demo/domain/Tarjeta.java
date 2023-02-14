package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tarjeta")
public class Tarjeta {

    @Id
    @Column(name = "id_tarjeta")
    private long id;

    //@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$")
    @Column(length = 16)
    private String numero;

    //@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$")
    @Column(length = 5)
    private String caducidad;

    @OneToOne
    @JoinColumn(name = "id_tarjeta", foreignKey = @ForeignKey(name = "FK_SOCIO"))
    @MapsId
    @JsonIgnore
    @ToString.Exclude
    private Socio socio;
}
