package BasicsProjects.QuizApp.service;

import BasicsProjects.QuizApp.Dao.QuestionDao;
import BasicsProjects.QuizApp.Entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<String> addQuestion(Questions questions) {
        questionDao.save(questions);
        return new ResponseEntity<>( "success",HttpStatus.CREATED);

    }
    public Page<Questions> getQuestions(Pageable pageable) {
        return questionDao.findAll(pageable);
    }

    public Page<Questions> QustionsByCategory(String category,Pageable pageable) {
        return (Page<Questions>) questionDao.findByCategory(category,pageable);
    }
}
