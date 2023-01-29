package ru.itmentor.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private long id;
    private  String name;
    private String lastName;
    private  byte age;

}
