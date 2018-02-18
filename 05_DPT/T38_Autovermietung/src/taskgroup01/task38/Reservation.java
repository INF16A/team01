package taskgroup01.task38;

import taskgroup01.task38.command.CommandRent;
import taskgroup01.task38.command.ICommand;

import java.util.*;

public class Reservation {
    private Map<Integer, List<IReservationListener>> reservations = new HashMap<>();
    private Repository repository;

    public Reservation(Repository repository) {
        this.repository = repository;
        for (int i = 0; i < 5; i++) {
            reservations.put(i + 1, new ArrayList<>());
        }

    }

    public void sendNotification(int type) {
        if(reservations.get(type).size() > 0) {
            IReservationListener listener = reservations.get(type).remove(0);
            listener.reservationNotification();
            ICommand rent = new CommandRent((Customer)listener, type, repository);
            rent.execute();
        }
    }

    public void addListener(int type, IReservationListener listener) {
        //listeners.add(listener);
        reservations.get(type).add(listener);
    }

    public void removeListener(int type, IReservationListener listener) {
        reservations.get(type).remove(listener);
    }
}
