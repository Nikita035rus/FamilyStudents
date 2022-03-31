package studentorder.student_order;

import studentorder.dao.DictionaryDaoImpl;
import studentorder.dao.StudentOrderDao;
import studentorder.dao.StudentOrderDaoImpl;
import studentorder.domain.CountryArea;
import studentorder.domain.Street;
import studentorder.domain.StudentOrder;

import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception{
//        List<Street> d = new DictionaryDaoImpl().findStreets("q");
//        for(Street s:d){
//            System.out.println(s.getStreetName());
//        }
//        List<CountryArea> countryAreas = new DictionaryDaoImpl().findAreas("");
//        for(CountryArea c : countryAreas){
//            System.out.println(c.getAreaID()+":"+c.getAreaName());
//        }
//        List<CountryArea> countryAreas2 = new DictionaryDaoImpl().findAreas("020000000000");
//        for(CountryArea c : countryAreas2){
//            System.out.println(c.getAreaID()+":"+c.getAreaName());
//        }
//        List<CountryArea> countryAreas23 = new DictionaryDaoImpl().findAreas("020010000000");
//        for(CountryArea c : countryAreas23){
//            System.out.println(c.getAreaID()+":"+c.getAreaName());
//        }
//        List<CountryArea> countryAreas233 = new DictionaryDaoImpl().findAreas("020010010000");
//        for(CountryArea c : countryAreas233){
//            System.out.println(c.getAreaID()+":"+c.getAreaName());
//        }

        StudentOrder s = buildStudentOrder(10);
        StudentOrderDao dao = new StudentOrderDaoImpl();
        Long id = dao.saveStudentOrder(s);
        System.out.println(id);
    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("SaveStudentOrder");
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id){
        return null;
    }
}
