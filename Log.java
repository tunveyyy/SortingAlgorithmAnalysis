import org.joda.time.DateTime;


public class Log implements Comparable<Log> {
    private DateTime dateTime;
    private String log;

    Log(String log) {
        this.dateTime = DateTime.parse(log.split(" ")[0]);
        this.log = log;
    }
    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public int compareTo(Log log) {
        return this.getDateTime().compareTo(log.getDateTime());
    }

    @Override
    public String toString() {
        return log;
    }
}
