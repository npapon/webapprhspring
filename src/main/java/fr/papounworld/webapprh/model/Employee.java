package fr.papounworld.webapprh.model;

import lombok.Data;

//ATTENTION LES PROPRIETES DOIVENT AVOIR LE MEME NOM QUE CELLE DE L OBJET DANS L API
@Data
public class Employee {

    private Integer id;

    private String firstname;

    private String lastname;

    private String mail;

    private String password;

}