package util;

import personDao.bean.Person;

public class CreatePerson {

    public static final String JOHN = "John";
    public static final String SMITH = "Smith";

    public static Person create(){
        return Person.builder().name(JOHN).surname(SMITH).build();
    }
}
