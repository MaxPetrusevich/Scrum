package converter;

import dto.PersonDto;
import servlet.Constants;

import javax.servlet.http.HttpServletRequest;

public class Converter {
    public static PersonDto convert(HttpServletRequest request) {
        String name = request.getParameter(Constants.FIRST_NAME);
        String surname = request.getParameter(Constants.LAST_NAME);
        PersonDto personDto = PersonDto.builder().name(name).surname(surname).build();
        String id = request.getParameter(Constants.ID);
        if(id != null){
            personDto.setId(Integer.parseInt(id));
        }
        return personDto;
    }
}
