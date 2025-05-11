import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

/**
 * An abstract base class for generating various types of reports.
 * Provides common functionality for report generation including
 * directory creation and filename generation with timestamps.
 */
public abstract class ReportGenerator {
    /** The directory where all reports will be saved */
    protected static final String REPORTS_DIR = "reports/";

    /**
     * Constructs a ReportGenerator and ensures the reports directory exists.
     * Creates the reports directory if it doesn't already exist.
     */
    public ReportGenerator() {
        new File(REPORTS_DIR).mkdirs();
    }

    /**
     * Generates a unique filename with the given prefix and current timestamp.
     * The file will be created in the reports directory.
     *
     * @param prefix the prefix to use for the filename
     * @return the complete file path including directory, prefix, and timestamp
     */
    protected String generateFileName(String prefix) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return REPORTS_DIR + prefix + "_" + timestamp + ".txt";
    }

    /**
     * Abstract method to be implemented by subclasses for generating specific reports.
     * Each subclass should provide its own implementation of report generation logic.
     */
    public abstract void generateReport();
}
