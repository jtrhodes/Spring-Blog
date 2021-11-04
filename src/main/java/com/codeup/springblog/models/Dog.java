package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "tinyint(3) unsigned not null")
    private int age;
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String name;
    @Column(columnDefinition = "char(2) DEFAULT 'XX'")
    private String resideState;

    public Dog(){
    }

    public Dog(long id, int age, String name, String resideState) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}


//    CREATE TABLE `dogs` (
//        `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//        `age` tinyint(3) unsigned NOT NULL,
//        `name` varchar(200) NOT NULL,
//        `reside_state` char(2) DEFAULT 'XX',
//        PRIMARY KEY (`id`),
//        UNIQUE KEY `UK_?????????????????` (`age`)
//        ) ENGINE=<WHATEVER_VALUE_HERE> DEFAULT CHARSET=utf8