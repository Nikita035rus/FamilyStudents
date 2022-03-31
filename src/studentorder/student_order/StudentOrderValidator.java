package studentorder.student_order;

import studentorder.domain.children.AnswerChildren;
import studentorder.domain.register.AnswerCityRegister;
import studentorder.domain.students.AnswerStudent;
import studentorder.domain.wedding.AnswerWedding;
import studentorder.domain.StudentOrder;
import studentorder.mail.MailSender;
import studentorder.validator.ChildrenValidator;
import studentorder.validator.CityRegisterValidator;
import studentorder.validator.StudentValidator;
import studentorder.validator.WeddingValidator;

import java.util.List;

public class StudentOrderValidator {
    /*
    валидация
     */

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator(CityRegisterValidator cityRegisterVal,
                                 WeddingValidator weddingVal,
                                 ChildrenValidator childrenVal,
                                 StudentValidator studentVal,
                                 MailSender mailSender) {
        this.cityRegisterVal = cityRegisterVal;
        this.weddingVal = weddingVal;
        this.childrenVal = childrenVal;
        this.studentVal = studentVal;
        this.mailSender = mailSender;
    }

    public static void main(String[] args) {

    }

    public static void checkAll() {
    }

    //чтение студентческой заявки
    public static List<StudentOrder> readStudentOrder() {
        return null;
    }

    //проверка города регистрации
    public static AnswerCityRegister checkCityRegister() {
        return null;
    }

    public static AnswerWedding checkWedding() {
        return null;
    }

    public static AnswerChildren checkChildren() {
        return null;
    }

    //проверка студента
    public static AnswerStudent checkStudent() {
        return null;
    }

    //отсылает на почту о результате проверки
    public static void sendMail() {
    }


}
