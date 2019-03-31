package by.itakademy.akulov.mapper;

import by.itakademy.akulov.dao.CourseTypeDao;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.entity.Course;

import java.time.LocalDate;

public class CourseMapper implements BaseMapper<Course, CourseDto> {

    private static final CourseMapper INSTANCE = new CourseMapper();
    private CourseTypeDao courseTypeDao = CourseTypeDao.getInstance();

    @Override
    public CourseDto mapToDto(Course entity) {
        return CourseDto.builder()
                .id(entity.getId())
                .type(entity.getType().getName())
                .name(entity.getName())
                .startdate(entity.getStartDate().toString())
                .duration(entity.getDuration())
                .description(entity.getDescription())
                .plan(entity.getPlan())
                .build();
    }

    @Override
    public Course mapToEntity(CourseDto dto) {
        return Course.builder()
                .id(null)
                .type(courseTypeDao.getEntityByName(dto.getType()).isPresent()
                        ? (courseTypeDao.getEntityByName(dto.getType())).get()
                        : null)
                .startDate(LocalDate.parse(dto.getStartdate()))
                .duration(dto.getDuration())
                .name(dto.getName())
                .description(dto.getDescription())
                .plan(dto.getPlan())
                .build();
    }

    public static CourseMapper getInstance() {
        return INSTANCE;
    }
}
