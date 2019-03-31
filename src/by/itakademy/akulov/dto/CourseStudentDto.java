package by.itakademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseStudentDto {

    private CourseUserDto courseUserDto;
    private CourseDto courseDto;
    private Integer result;

}
