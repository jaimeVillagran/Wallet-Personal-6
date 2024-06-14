package cl.project.walletprofesional.service.impl;

import cl.project.walletprofesional.entity.User;
import cl.project.walletprofesional.repository.UserRepository;
import cl.project.walletprofesional.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User getAttibute(String user) {
        return null;
    }

    @Override
    public User updateUserBalance(User user, double amount) {
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            userRepository.save(user);
        }
        return user;
    }
}
