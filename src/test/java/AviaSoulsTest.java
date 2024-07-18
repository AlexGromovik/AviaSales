import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Москва", "Новосибирск", 15_000, 8, 13);
    Ticket ticket2 = new Ticket("Казань", "Новосибирск", 18_000, 13, 16);
    Ticket ticket3 = new Ticket("Москва", "Самара", 8_000, 12, 15);
    Ticket ticket4 = new Ticket("Москва", "Новосибирск", 18_000, 13, 17);
    Ticket ticket5 = new Ticket("Казань", "Новосибирск", 12_000, 9, 12);
    Ticket ticket6 = new Ticket("Москва", "Новосибирск", 13_000, 22, 4);

    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void testCompareToShouldReturnOneForHighestPriceTicket(){
        System.out.println(ticket1.compareTo(ticket6));

        int expected = 1;
        int actual = ticket1.compareTo(ticket6);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCompareToShouldReturnZeroForEqualTicketPrices(){
        System.out.println(ticket2.compareTo(ticket4));

        int expected = 0;
        int actual = ticket2.compareTo(ticket4);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCompareToShouldReturnMinusOneForTicketWithLowerPrice(){
        System.out.println(ticket3.compareTo(ticket5));

        int expected = -1;
        int actual = ticket3.compareTo(ticket5);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void searchShouldReturnTicketsSortByPrice(){
        Ticket[] expected = {ticket6,ticket1,ticket4};
        Ticket[] actual = manager.search("Москва", "Новосибирск");

        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void searchShouldReturnTicketWithOnlyMatch(){
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.search("Москва", "Самара");

        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void searchShouldReturnEmptyWhenNoneMatches(){
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Москва", "Екатеринбург");

        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void searchAndSortByShouldSearchAndSortByFlyTime(){
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket1, ticket6};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Новосибирск", comparator);

        Assertions.assertArrayEquals(expected,actual);
    }

}
