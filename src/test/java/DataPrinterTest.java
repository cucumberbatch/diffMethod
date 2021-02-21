import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataPrinter;
import problem.utils.view.impl.CSVFormatFileDataPrinter;
import problem.utils.view.impl.FileDataPrinter;

import java.io.IOException;


class DataPrinterTest {

    FieldConfiguration configuration = new FieldConfiguration(new double[1000][1000], 100, 100);


    @Test
    void testRawDataFilePrinter() throws IOException {
        DataPrinter printer = new FileDataPrinter("raw-data");
        printer.print(configuration);
    }

    @Test
    void testCSVDataFilePrinter() throws IOException {
        DataPrinter printer = new CSVFormatFileDataPrinter("csv-data");
        printer.print(configuration);
    }
}