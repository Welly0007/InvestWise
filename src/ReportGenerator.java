import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public abstract class ReportGenerator {
    protected static final String REPORTS_DIR = "reports/";

    public ReportGenerator() {
        new File(REPORTS_DIR).mkdirs();
    }

    protected String generateFileName(String prefix) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return REPORTS_DIR + prefix + "_" + timestamp + ".txt";
    }

    public abstract void generateReport();
}
