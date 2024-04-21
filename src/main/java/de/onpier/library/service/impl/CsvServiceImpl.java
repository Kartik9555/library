package de.onpier.library.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import de.onpier.library.model.csv.BookCsvDto;
import de.onpier.library.model.csv.BorrowedCsvDto;
import de.onpier.library.model.csv.UserCsvDto;
import de.onpier.library.service.CsvService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public List<UserCsvDto> convertUserCSV(File csvFile) {
        try {
            return new CsvToBeanBuilder<UserCsvDto>(new FileReader(csvFile))
                .withType(UserCsvDto.class)
                .build()
                .parse();
        }
        catch (FileNotFoundException e) {
            log.error("Error occurred while reading user csv file", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<BorrowedCsvDto> convertBorrowedCSV(File csvFile) {
        try {
            return new CsvToBeanBuilder<BorrowedCsvDto>(new FileReader(csvFile))
                .withType(BorrowedCsvDto.class)
                .build()
                .parse();
        }
        catch (FileNotFoundException e) {
            log.error("Error occurred while reading borrowed csv file", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<BookCsvDto> convertBookCSV(File csvFile) {
        try {
            return new CsvToBeanBuilder<BookCsvDto>(new FileReader(csvFile))
                .withType(BookCsvDto.class)
                .build()
                .parse();
        }
        catch (FileNotFoundException e) {
            log.error("Error occurred while reading book csv file", e);
            throw new RuntimeException(e);
        }
    }
}
