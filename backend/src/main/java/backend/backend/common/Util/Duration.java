package backend.backend.common.Util;

import jakarta.persistence.Embeddable;

@Embeddable
public class Duration {
    private final int minutes;
    private final int seconds;

    private Duration(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static Duration of(String duration) {
        if(duration == null || !duration.matches("\\d{1,2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid duration format. Expected mm:ss");
        }
        String[] parts = duration.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return new Duration(minutes, seconds);
    }

    public static Duration of(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException("Invalid duration values");
        }
        return new Duration(minutes, seconds);
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", minutes, seconds);
    }


}
