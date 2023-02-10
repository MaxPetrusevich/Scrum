package service;

import dto.PersonDto;
import lombok.Getter;

import java.util.List;

public interface PersonService {
    PersonDto create(PersonDto personDto);
    List<PersonDto> findAll();
    PersonDto update(PersonDto personDto);
    int delete(Integer id);

    PersonDto findById(Integer id);

}
