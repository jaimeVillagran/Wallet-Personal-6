package cl.project.walletprofesional.service;

import cl.project.walletprofesional.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUserById(long id);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    User getAttibute(String user);
    User updateUserBalance(User user, double amount);
}