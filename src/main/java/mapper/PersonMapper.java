package mapper;

import dto.PersonDto;
import org.mapstruct.Mapper;
import bean.Person;

@Mapper
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);


    Person personDtoToPerson(PersonDto dto);
}
