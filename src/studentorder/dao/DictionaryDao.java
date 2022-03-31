package studentorder.dao;

import studentorder.domain.PassportOffice;
import studentorder.domain.RegisterOffice;
import studentorder.domain.Street;
import studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<RegisterOffice> findRegisterOffices(String areaID) throws DaoException;
    List<PassportOffice> findPassportOffices(String areaID) throws DaoException;
}
