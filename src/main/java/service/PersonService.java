package service;



import dto.PersonDto;

import lombok.SneakyThrows;
import mapper.PersonMapperImpl;
import personDao.bean.Person;
import personDao.dao.DaoPerson;
import personDao.service.DaoPersonImpl;
import personDao.util.DataForTable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;

public class PersonService {
    private static final PersonService INSTANCE = new PersonService();
    private final DaoPerson personDao = getDao();
    private final PersonMapperImpl mapper = new PersonMapperImpl();

    public PersonDto create(PersonDto personDto) throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Person personEntity = mapper.personDtoToPerson(personDto);
        personEntity = personDao.save(personEntity);
        return mapper.personToPersonDto(personEntity);
    }
    public List<PersonDto> findAll() throws SQLException {
        return personDao.selectAll().stream()
                .map(person ->mapper.personToPersonDto(person)
                )
        .collect(toList());
    }
    @SneakyThrows
    private DaoPersonImpl getDao(){
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        return new DaoPersonImpl(data);
    }



    @SneakyThrows
    public Integer update(PersonDto personDto) {
        Person personEntity = mapper.personDtoToPerson(personDto);
        personDao.update(personEntity);
        return personEntity.getId();
    }

    @SneakyThrows
    public int delete(Integer id) {
        return personDao.delete(id);
    }


    public static PersonService getInstance() {

        return INSTANCE;
    }
    private PersonService() {
    }
}
