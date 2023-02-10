package service;

import dto.PersonDto;
import mapper.PersonMapper;
import mapper.PersonMapperImpl;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;
import util.CreatePersonDto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static personDao.h2Test.TestPerson.WAY_TO_SCRIPT;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonServiceImplTest {
    public static final String TEST = "Test";
    public static final String DTO = "Dto";
    public static final String UPDATE_TEST = "UpdateTest";
    private PersonServiceImpl personService;


    @BeforeAll
    public void init() throws SQLException, FileNotFoundException {
        personService = PersonServiceImpl.getInstance();
        RunScript.execute(personService.getPersonDao().getConn(), new FileReader(WAY_TO_SCRIPT));
    }


    @Test
    void create() {
        assertNotNull(personService.create(CreatePersonDto.create(TEST, DTO)));
    }

    @Test
    void findAll() {
        assertNotNull(personService.findAll());
    }

    @Test
    void update() {
        PersonDto personDto = CreatePersonDto.create(TEST, DTO);
        personDto = personService.create(personDto);
        personDto.setName(UPDATE_TEST);
        personService.update(personDto);
        PersonDto personDtoTest = personService.findById(personDto.getId());
        assertEquals(personDto.getName(), personDtoTest.getName());
    }

    @Test
    void delete() {
        PersonDto personDto = CreatePersonDto.create(TEST, DTO);
        personDto = personService.create(personDto);
        int rows = personService.delete(personDto.getId());
        assertEquals(1, rows);
    }

    @Test
    void getInstance() {
        assertNotNull(personService);
    }
}
