package de.onpier.library.model.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String firstName;
    private LocalDate memberSince;
    private LocalDate memberTill;
    private String gender;
}
