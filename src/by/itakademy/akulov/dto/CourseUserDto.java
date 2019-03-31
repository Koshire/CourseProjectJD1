package by.itakademy.akulov.dto;

import by.itakademy.akulov.entity.Language;
import by.itakademy.akulov.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUserDto {

    private Integer id;
    private Role role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String passphrase;
    private Language lang;
}
