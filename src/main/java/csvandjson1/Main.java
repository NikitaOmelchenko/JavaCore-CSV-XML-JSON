package csvandjson1;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;



public class Main {

    public static void main(String[] args) throws Exception {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String outFileName = "data.json";

        List<Employee> list = parseCSV(columnMapping, fileName);

        System.out.println(outFileName + " created");
    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) throws IOException {
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(',')
                    .build();

            return csvToBean.parse();
        }
    }

}