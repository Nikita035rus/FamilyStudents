package studentorder.validator.register;

import studentorder.domain.register.CityRegisterResponse;
import studentorder.domain.Person;
import studentorder.exception.CityRegisterException;

public class RealCityRegisterChecker implements CityRegisterChecker{

    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
