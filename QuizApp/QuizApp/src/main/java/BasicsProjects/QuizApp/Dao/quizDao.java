package BasicsProjects.QuizApp.Dao;

import BasicsProjects.QuizApp.Entities.Questions;
import BasicsProjects.QuizApp.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface quizDao extends JpaRepository<Quiz,Integer>{

}
