package by.itakademy.akulov.service;

import by.itakademy.akulov.dao.CourseDao;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.entity.Course;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import by.itakademy.akulov.mapper.CourseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseService {

    private static final CourseService INSTANCE = new CourseService();


    private CourseDao courseDao = CourseDao.getInstance();
    private CourseMapper mapper = CourseMapper.getInstance();

    public List<CourseDto> findAll() {
        return courseDao.getAllCourse().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<CourseDto> find(Integer id) {
        return courseDao.getCourseById(id)
                .map(mapper::mapToDto);
    }

    public void delete(Integer id) {
        courseDao.delete(id);
    }

    public Course save(CourseDto dto) {
        return courseDao.save(mapper.mapToEntity(dto));
    }

    public static CourseService getInstance() {
        return INSTANCE;
    }
}
