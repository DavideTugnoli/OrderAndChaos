package graphicui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiSettingsTest {

    private UiSettings uiSettings;

    @BeforeEach
    public void setUp() {
        uiSettings = UiSettings.getInstance();
    }

    @Test
    public void testGetInstance() {
        UiSettings instance1 = UiSettings.getInstance();
        UiSettings instance2 = UiSettings.getInstance();

        Assertions.assertSame(instance1, instance2, "Multiple instances of UiSettings were created.");
    }

    @Test
    public void testDefaultSoundEnabled() {
        boolean soundEnabled = uiSettings.isSoundEnabled();
        Assertions.assertTrue(soundEnabled, "Default soundEnabled should be true.");
    }

    @Test
    public void testSetSoundEnabled() {
        uiSettings.setSoundEnabled(false);
        boolean soundEnabled = uiSettings.isSoundEnabled();
        Assertions.assertFalse(soundEnabled, "setSoundEnabled(false) should set soundEnabled to false.");

        uiSettings.setSoundEnabled(true);
        soundEnabled = uiSettings.isSoundEnabled();
        Assertions.assertTrue(soundEnabled, "setSoundEnabled(true) should set soundEnabled to true.");
    }
}

