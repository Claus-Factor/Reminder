import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ReminderManager manager = new ReminderManager();

        Reminder reminder1 = new Reminder(Reminder.Priority.MEDIUM, "Лаба_КГ_1", "Сделать полностью лабу", "01/10/2024 12:50");
        Reminder reminder2 = new Reminder(Reminder.Priority.LOW, "Лаба3_КГ", "Выполнить две части", "14/11/2024 09:45");

        manager.add(reminder1);
        manager.add(reminder2);

        System.out.println("Просроченные напоминания: ");
        List<Reminder> overdueReminders = manager.getOverdueReminders();
        for (Reminder reminder : overdueReminders) {
            System.out.println(reminder.toString());
        }

        System.out.println("Планируемые напоминания: ");
        List<Reminder> upcomingReminders = manager.getUpcomingReminders();
        for (Reminder reminder : upcomingReminders) {
            System.out.println(reminder.toString());
        }

        System.out.println("Ближайшее напоминание: ");
        Optional<Reminder> nextReminder = manager.getNextReminder();
        if (nextReminder.isPresent()) {
            System.out.println(nextReminder.get().toString());
        }

        System.out.println("Все напоминания: ");
        List<Reminder> reminders = manager.getReminders();
        for (Reminder reminder : reminders) {
            System.out.println(reminder.toString());
        }

        System.out.println("Все напоминания по приоритетам: ");
        List<Reminder> reminders1 = manager.getRemindersSortedByPriority();
        for (Reminder reminder : reminders1) {
            System.out.println(reminder.toString());
        }


        System.out.println("Изменим дедлайн для 3-й лабы");
        manager.rescheduleReminder(reminder2, LocalDateTime.of(2024,11,7,9,45));
        System.out.println("Ближайшее напоминание: ");
        nextReminder = manager.getNextReminder();
        if (nextReminder.isPresent()) {
            System.out.println(nextReminder.get().toString());
        }


    }
}