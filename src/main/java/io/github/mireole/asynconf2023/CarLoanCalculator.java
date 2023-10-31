package io.github.mireole.asynconf2023;


import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.IntelliJTheme;
import io.github.mireole.asynconf2023.backend.Config;
import io.github.mireole.asynconf2023.backend.ConfigReader;
import io.github.mireole.asynconf2023.gui.Window;

import java.util.logging.Logger;

public class CarLoanCalculator {
    public static final Logger LOGGER = Logger.getLogger(CarLoanCalculator.class.getName());

    public static void main(String[] args) {
        Config config = ConfigReader.readConfig();
        // Setup look and feel using system default
        LafManager.installTheme(new IntelliJTheme());
        new Window(config);

    }

}
