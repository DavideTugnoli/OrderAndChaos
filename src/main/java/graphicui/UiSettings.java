package graphicui;

public class UiSettings {
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
}
