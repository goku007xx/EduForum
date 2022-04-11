package springmongo.m_app;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
	Optional<Teacher> findTeacherByEmail(String email);
}
