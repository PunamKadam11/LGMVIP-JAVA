package BasicsProjects.QuizApp.Dao;

import BasicsProjects.QuizApp.Entities.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {
     List<Questions> findByCategory(String category);
     Page<Questions> findByCategory(String category, Pageable pageable);

     @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
     List<Questions> findRandomQuestionsByCategory(@Param("category") String category, @Param("noOfQuestions") int noOfQuestions);
}
