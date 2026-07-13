package com.testsquad.crud.modules.staffregistration.application;

import com.testsquad.crud.modules.staffregistration.application.exception.CpfAlreadyRegisteredException;
import com.testsquad.crud.modules.staffregistration.application.exception.EmailAlreadyRegisteredException;
import com.testsquad.crud.modules.staffregistration.domain.model.Role;
import com.testsquad.crud.modules.staffregistration.domain.model.StaffProfile;
import com.testsquad.crud.modules.staffregistration.domain.model.User;
import com.testsquad.crud.modules.staffregistration.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Service
public class RegisterStaffService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterStaffService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterStaffCommand command) {
        validatePassword(command.password());

        String normalizedCpf = command.cpf() != null ? command.cpf().replaceAll("\\D", "") : null;
        String normalizedEmail = command.email() != null ? command.email().trim().toLowerCase() : null;

        if (normalizedCpf != null && userRepository.existsByCpf(normalizedCpf)) {
            throw new CpfAlreadyRegisteredException(normalizedCpf);
        }

        if (normalizedEmail != null && userRepository.existsByEmail(normalizedEmail)) {
            throw new EmailAlreadyRegisteredException(normalizedEmail);
        }

        User user = User.create(
                command.fullName(),
                command.socialName(),
                command.cpf(),
                command.email(),
                passwordEncoder.encode(command.password()),
                command.gender(),
                command.lattesLink(),
                command.phone(),
                new StaffProfile(
                        command.staffType(),
                        command.campus(),
                        command.academicDegree(),
                        command.educationArea()
                ),
                Set.of(Role.ROLE_COORDINATOR, Role.ROLE_REVIEWER)
        );

        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must contain at least 6 characters");
        }
    }
}
