package com.example.EduForums.subject;

import java.util.List;
import java.util.Optional;

import com.example.EduForums.teacher.Teacher;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
	Optional<Subject> findSubjectBySubjectCode(String subjectCode);
	List<Subject> findSubjectBySubjectTeacher(Teacher teacher);

}
