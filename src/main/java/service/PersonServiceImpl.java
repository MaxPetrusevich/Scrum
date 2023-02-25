package service;


import dto.PersonDto;

import lombok.Getter;
import lombok.SneakyThrows;
import mapper.PersonMapperImpl;
import bean.Person;
import personDao.service.DaoPersonImpl;
import personDao.util.DataForTable;

import java.util.List;


import static java.util.stream.Collectors.toList;

@Getter
public class PersonServiceImpl implements PersonService {
    private static final PersonServiceImpl INSTANCE = new PersonServiceImpl();
    private final DaoPersonImpl personDao = getDao();
    private final PersonMapperImpl mapper = new PersonMapperImpl();

    @SneakyThrows
    @Override
    public PersonDto create(PersonDto personDto) {
        Person personEntity = mapper.personDtoToPerson(personDto);
        personEntity = personDao.save(personEntity);
        return mapper.personToPersonDto(personEntity);
    }

    @SneakyThrows
    @Override
    public List<PersonDto> findAll() {
        return personDao.selectAll().stream()
                .map(person -> mapper.personToPersonDto(person)
                )
                .collect(toList());
    }


    @SneakyThrows
    @Override
    public List<PersonDto> findLimit(int currentPage, int countRecords) {
        return personDao.selectLimit(currentPage,countRecords).stream()
                .map(person -> mapper.personToPersonDto(person)
                )
                .collect(toList());
    }

    @SneakyThrows
    @Override
    public Integer getCountRows() {
        return personDao.getNumberOfRows();
    }

    @SneakyThrows
    private DaoPersonImpl getDao() {
        DataForTable<Person> data = new DataForTable<>(new Person());
        data.updateInfoInData();
        return new DaoPersonImpl(data);
    }


    @Override
    @SneakyThrows
    public PersonDto update(PersonDto personDto) {
        Person personEntity = mapper.personDtoToPerson(personDto);
        personDao.update(personEntity);
        return mapper.personToPersonDto(personEntity);
    }

    @SneakyThrows
    public int delete(Integer id) {
        return personDao.delete(id);
    }

    @SneakyThrows
    @Override
    public PersonDto findById(Integer id) {
        return mapper.personToPersonDto(personDao.get(id));
    }


    public static PersonServiceImpl getInstance() {

        return INSTANCE;
    }

    private PersonServiceImpl() {
    }
}
