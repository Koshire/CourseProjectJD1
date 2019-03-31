package by.itakademy.akulov.service;

import by.itakademy.akulov.dao.CourseUserDao;
import by.itakademy.akulov.dao.RoleDao;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.CourseUser;
import by.itakademy.akulov.entity.Role;
import by.itakademy.akulov.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleService {

    private static final RoleService INSTANCE = new RoleService();

    RoleDao roleDao = RoleDao.getInstance();

    public List<Role> findAll() {
        return roleDao.getAll();
    }

    public Optional<Role> getById (Integer id) {
        return roleDao.getEntityById(id);
    }


    public static RoleService getInstance() {
        return INSTANCE;
    }


}
