package de.onpier.library.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCsvDto {
    @CsvBindByName
    private String name;
    @CsvBindByName(column = "First name")
    private String firstName;

    @CsvBindByName(column = "Member since")
    @CsvDate(value = "MM/dd/yyyy")
    private LocalDate memberSince;

    @CsvBindByName(column = "Member till")
    @CsvDate(value = "MM/dd/yyyy")
    private LocalDate memberTill;
    @CsvBindByName
    private String gender;
}
