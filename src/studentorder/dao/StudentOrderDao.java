package studentorder.dao;

import studentorder.domain.StudentOrder;
import studentorder.exception.DaoException;

public interface StudentOrderDao {
    //сохранение студентческой заявки
     Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;
}
