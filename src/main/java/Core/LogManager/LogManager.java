package Core.LogManager;

import org.apache.logging.log4j.Logger;

public class LogManager {
    public static final String LOG_PATH = "test-output/logs/";

    private static Logger Logger()
    {
        return org.apache.logging.log4j.LogManager.getLogger(Thread.currentThread().
                getStackTrace()[3].getClassName());
    }
    public static void Info(String... message) {
        Logger().info(String.join("", message));

    }

    public static void Error(String... message) {
        Logger().error(String.join("", message));
    }

    public static void Debug(String... message) {
        Logger().debug(String.join("", message));
    }

    public static void Warn(String... message) {
        Logger().warn(String.join("", message));
    }

    public static void Fatal(String... message) {
        Logger().fatal(String.join("", message));
    }

    public static void Trace(String... message) {
        Logger().trace(String.join("", message));
    }
}
