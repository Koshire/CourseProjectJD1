package by.itakademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Integer id;
    private String type;
    private String name;
    private String startdate;
    private Integer duration;
    private String description;
    private String plan;
}
