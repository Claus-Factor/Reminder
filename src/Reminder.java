import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Reminder {
    enum Priority {
        LOW, MEDIUM, HIGH
    }

    private Priority priority;
    private String title;
    private String description;
    private LocalDateTime reminderDateTime;

    public Reminder(String title, String description) {
        this.priority = Priority.LOW;
        this.title = title;
        this.description = description;
        reminderDateTime = LocalDateTime.now().plusDays(1);
    }
    public Reminder(Priority priority, String title, String description, LocalDateTime reminderDateTime) {
        this(title, description);
        this.priority = priority;
        this.reminderDateTime = reminderDateTime;
    }
    public Reminder(Priority priority, String title, String description, String reminderDateTime) {
        this(title, description);
        this.priority = priority;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.reminderDateTime = LocalDateTime.parse(reminderDateTime, df);
    }

    public boolean isReminderDue() {
        LocalDateTime current = LocalDateTime.now();
        return current.isEqual(reminderDateTime);
    }

    public long timeUntilReminder(TemporalUnit unit) {
        LocalDateTime current = LocalDateTime.now();
        return unit.between(current, reminderDateTime);
    }

    public String toString() {
        String strDateTime = reminderDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return "[" + priority + "] " + title + ". " + description + ". " + strDateTime;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void reschedule(LocalDateTime newDateTime) {
        reminderDateTime = LocalDateTime.of(newDateTime.toLocalDate(), newDateTime.toLocalTime());
    }
}