import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReminderManager {
    private List<Reminder> list;



    public ReminderManager() {
        list = new ArrayList<>();
    }

    public void add(Reminder reminder) {
        list.add(reminder);
    }

    public List<Reminder> getOverdueReminders() {
        LocalDateTime current = LocalDateTime.now();

        List<Reminder> overdueReminders = list.stream().filter(reminder -> reminder.getReminderDateTime().isBefore(current)).toList();
        return overdueReminders;
    }

    public List<Reminder> getUpcomingReminders() {
        LocalDateTime now = LocalDateTime.now();

        List<Reminder> upcomingReminders = list.stream().filter(reminder -> {
            boolean isThisUpcoming = reminder.getReminderDateTime().isAfter(now);
            return isThisUpcoming;
        }).toList();
        return upcomingReminders;
    }

    public Optional<Reminder>  getNextReminder() {
        Optional<Reminder> nextReminder = list.stream()
                .filter(reminder -> reminder.getReminderDateTime().isAfter(LocalDateTime.now()))
                .min((r1,r2) -> r1.getReminderDateTime().compareTo(r2.getReminderDateTime()));
        return nextReminder;
    }

    public List<Reminder> getReminders() {
        List<Reminder> reminders = list.stream().sorted((r1,r2) -> r1.getReminderDateTime().compareTo(r2.getReminderDateTime())).toList();
        return reminders;
    }

    public List<Reminder> getRemindersSortedByPriority() {
        List<Reminder> reminders = list.stream().sorted((r2,r1) -> r1.getPriority().compareTo(r2.getPriority())).toList();
        return reminders;
    }

    public void rescheduleReminder(Reminder reminder, LocalDateTime newDateTime) {
        if (list.contains(reminder)) {
            reminder.reschedule(newDateTime);
        } else {
            System.out.println("Напоминание не найдено!");
        }
    }

}