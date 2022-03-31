package studentorder.validator.register;

import studentorder.domain.Adult;
import studentorder.domain.register.CityRegisterResponse;
import studentorder.domain.Person;
import studentorder.exception.CityRegisterException;
import studentorder.exception.TransportException;

public class FakeCityRegisterChecker {
        private static final String GOOD_1 = "1000";
    public CityRegisterResponse checkPerson(Person person)
            throws CityRegisterException, TransportException {
        if(person instanceof Adult){
            Adult t = (Adult) person;
            if(t.getPassportSeria().equals("10000")) {

            }
        }
        return null;
    }

}
