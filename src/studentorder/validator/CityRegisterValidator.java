package studentorder.validator;

import studentorder.domain.Person;
import studentorder.domain.register.AnswerCityRegister;
import studentorder.domain.register.AnswerCityRegisterItem;
import studentorder.domain.register.CityRegisterResponse;
import studentorder.domain.StudentOrder;
import studentorder.exception.CityRegisterException;
import studentorder.exception.TransportException;
import studentorder.validator.register.RealCityRegisterChecker;

public class CityRegisterValidator {
    /*
    собрать ответы в пачку и поместить в answer
     */
    private String hostName;
    private int port;
    private String login;
    private String password;

    private RealCityRegisterChecker personChecker;


    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));
        return null;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityError error = null;
        try {
            personChecker.checkPerson(person);
        } catch (CityRegisterException /*| TransportException */e  ) {
            e.printStackTrace();
        }
        return null;
    }
}
