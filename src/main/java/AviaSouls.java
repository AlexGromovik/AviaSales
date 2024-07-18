import java.util.Arrays;
import java.util.Comparator;

public class AviaSouls {

    private Ticket[] tickets = new Ticket[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current Массив, в который мы хотим добавить элемент
     * @param ticket  Элемент, который мы хотим добавить
     * @return Возвращает новый массив, который выглядит как тот что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Ticket[] addToArray(Ticket[] current, Ticket ticket) {
        Ticket[] tmp = new Ticket[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = ticket;
        return tmp;
    }

    /**
     * Метод добавления билета в менеджер
     *
     * @param ticket Добавляемый билет
     */
    public void add(Ticket ticket) {
        tickets = addToArray(tickets, ticket);
    }

    public Ticket[] findAll() {
        return tickets;
    }

    /**
     * Метод поиска билетов по маршруту
     *
     * @param from Откуда вылетаем
     * @param to   Куда прилетаем
     * @return Массив из подходящих билетов
     */
    public Ticket[] search(String from, String to) {
        Ticket[] result = new Ticket[0]; // массив для ответа
        for (Ticket ticket : tickets) { // перебираем все билеты
            if (ticket.getFrom().equals(from)) { // совпадает аэропорт вылета
                if (ticket.getTo().equals(to)) { // совпадает аэропорт прилёта
                    result = addToArray(result, ticket); // добавляем его в массив ответа
                }
            }
        }
        Arrays.sort(result); // сортируем наш массив по возрастанию цены
        return result;
    }

    public Ticket[] searchAndSortBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0]; // новый массив для ответа
        for(Ticket ticket : tickets){ // перебираем все билеты в массиве
            if (ticket.getFrom().equals(from)){ // ищем совпадения в билетах по аэропорту вылета
                if (ticket.getTo().equals(to)){ // ищем совпадения в билетах по аэропорту прилета
                    result = addToArray(result, ticket); // добавляем совпавшие билеты в новый массив для ответа
                }
            }
        }
        Arrays.sort(result, comparator); // сортируем наш массив по возрастанию времени полета
        return result; // и возвращаем
    }
}
