package by.itakademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Course {

    private Integer id;
    private CourseType type;
    private LocalDate startDate;
    private Integer duration;
    private String name;
    private String description;
    private String plan;
}
