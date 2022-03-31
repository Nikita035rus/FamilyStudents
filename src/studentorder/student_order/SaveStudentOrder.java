package studentorder.student_order;

import studentorder.dao.DictionaryDaoImpl;
import studentorder.domain.Street;
import studentorder.domain.StudentOrder;

import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception{
        List<Street> d = new DictionaryDaoImpl().findStreets("q");
        for(Street s:d){
            System.out.println(s.getStreetName());
        }
    }
    static void saveStudentOrder(){
    }

    static StudentOrder buildStudentOrder(){
        return null;
    }
}
