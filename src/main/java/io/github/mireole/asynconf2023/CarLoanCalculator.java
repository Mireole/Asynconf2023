package io.github.mireole.asynconf2023;


import com.github.weisj.darklaf.LafManager;
import io.github.mireole.asynconf2023.backend.data.Config;
import io.github.mireole.asynconf2023.backend.data.ConfigReader;
import io.github.mireole.asynconf2023.gui.Window;

import java.util.logging.Logger;

public class CarLoanCalculator {
    public static final Logger LOGGER = Logger.getLogger(CarLoanCalculator.class.getName());
    public static void main(String[] args) {
        Config config = ConfigReader.readConfig();
        // Setup look and feel
        LafManager.install();
        Window window = new Window(config);

    }

}
