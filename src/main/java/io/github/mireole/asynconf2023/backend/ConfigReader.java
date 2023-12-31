package io.github.mireole.asynconf2023.backend;

import com.google.gson.Gson;
import io.github.mireole.asynconf2023.CarLoanCalculator;

import java.io.*;

public class ConfigReader {
    // Changed path to just file name
    private static final String DEFAULT_CONFIG_PATH = "/io/github/mireole/asynconf2023/default_config.json";
    private static final String CONFIG_PATH = "config.json";

    public static Config readConfig() {
        // If the config file exists in the current directory, use it
        File f = new File(CONFIG_PATH);
        if (f.exists() && !f.isDirectory()) {
            try (FileInputStream fis = new FileInputStream(f)) {
                return readConfig(fis);
            } catch (IOException e) {
                CarLoanCalculator.LOGGER.warning("Could not read config file: " + e.getMessage() + ", using default config file");
            }
        }
        // If it does not exist, use the default config file
        try (InputStream is = ConfigReader.class.getResourceAsStream(DEFAULT_CONFIG_PATH)) {
            // Check if inputStream is null, if null throw an exception
            if (is == null) {
                throw new NullPointerException("Could not find default config file");
            }
            return readConfig(is);
        } catch (IOException e) {
            CarLoanCalculator.LOGGER.severe("Could not read default config file: " + e.getMessage());
            throw new RuntimeException("Could not read default config file: " + e.getMessage());
        }
    }

    private static Config readConfig(InputStream path) {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(path)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            CarLoanCalculator.LOGGER.severe("Could not parse config file: " + e.getMessage());
            throw new RuntimeException("Could not parse config file: " + e.getMessage());
        }
    }

    public static void writeConfig(Config config) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            CarLoanCalculator.LOGGER.severe("Could not write config file: " + e.getMessage());
            throw new RuntimeException("Could not write config file: " + e.getMessage());
        }
    }
}