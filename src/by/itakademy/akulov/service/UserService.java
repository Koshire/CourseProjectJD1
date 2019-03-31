package by.itakademy.akulov.service;

import by.itakademy.akulov.dao.CourseUserDao;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.CourseUser;
import by.itakademy.akulov.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private CourseUserDao courseUserDao = CourseUserDao.getInstance();
    private UserMapper mapper = UserMapper.getInstance();

    public Optional<CourseUserDto> find(Integer id) {
        return courseUserDao.getUserById(id)
                .map(mapper::mapToDto);
    }

    public Optional<CourseUserDto> findLogin(String name, String passphrase) {
        return courseUserDao.getUserForLogin(name, passphrase)
                .map(mapper::mapToDto);
    }

    public CourseUser save(CourseUserDto dto) {
        return courseUserDao.save(mapper.mapToEntity(dto));
    }

    public CourseUser update(CourseUserDto dto) {
        return courseUserDao.update(mapper.mapToEntity(dto));
    }



    public static UserService getInstance() {
        return INSTANCE;
    }


}
