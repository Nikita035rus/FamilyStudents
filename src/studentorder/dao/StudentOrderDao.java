package studentorder.dao;

import studentorder.domain.StudentOrder;
import studentorder.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {
    //сохранение студентческой заявки
     Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;
     List<StudentOrder> getStudentOrders() throws DaoException;
}
