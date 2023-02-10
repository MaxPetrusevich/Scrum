package util;

import dto.PersonDto;

public class CreatePersonDto {
    public static PersonDto create(String name, String surname){
        return PersonDto.builder()
                .name(name)
                .surname(surname)
                .build();
    }
}
