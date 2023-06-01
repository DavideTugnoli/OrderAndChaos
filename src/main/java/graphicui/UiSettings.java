package graphicui;

import java.io.Serial;
import java.io.Serializable;

public class UiSettings implements Serializable {
    private static UiSettings instance = null;

    private boolean soundEnabled = true;

    private UiSettings() { }

    public static UiSettings getInstance() {
        if (instance == null) {
            instance = new UiSettings();
        }
        return instance;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    @Serial
    protected Object readResolve() {
        return getInstance();
    }

}
