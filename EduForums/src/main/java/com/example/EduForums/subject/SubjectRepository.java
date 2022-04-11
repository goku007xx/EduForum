package com.example.EduForums.subject;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
	Optional<Subject> findSubjectBySubjectCode(String subjectCode);

}
