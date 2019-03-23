package by.itakademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class CourseUser {

    private Integer id;
    private Role role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
    private String passphrase;
}
