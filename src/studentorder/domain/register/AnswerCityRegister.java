package studentorder.domain.register;

import java.util.ArrayList;
import java.util.List;

public class AnswerCityRegister {
    //структура возвращающая ответ на запрос

    private List<AnswerCityRegisterItem> items;

    public void addItem(AnswerCityRegisterItem item) {
        if (item == null) {
            items = new ArrayList<>();
        }
    }

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }
}
