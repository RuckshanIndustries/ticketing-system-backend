package com.ticketingsystemfrontend.demo.config;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;





    public class Logger {
        private static final String LOG_FILE = "system.log"; // Log file name
        private static boolean logToFile = false; // Default: log to console only


        public static void enableFileLogging(boolean enable) {
            logToFile = enable;
        }

        public static void log(String level, String message) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);

            // Print to console
            System.out.println(logMessage);

            // Write to file if enabled
            if (!logToFile) {
                try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
                    writer.write(logMessage + "\n");
                } catch (IOException e) {
                    System.err.println("[ERROR] Failed to write to log file: " + e.getMessage());
                }
            }
        }

        public static void info(String message) {
            log("INFO", message);
        }

        public static void warn(String message) {
            log("WARN", message);
        }

        public static void error(String message) {
            log("ERROR", message);
        }
    }


