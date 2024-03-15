package BasicsProjects.QuizApp.controller;

import BasicsProjects.QuizApp.Dao.quizDao;
import BasicsProjects.QuizApp.Entities.QuestionWrapper;
import BasicsProjects.QuizApp.Entities.Questions;
import BasicsProjects.QuizApp.Entities.Quiz;
import BasicsProjects.QuizApp.Entities.Responce;
import BasicsProjects.QuizApp.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
     @Autowired
    private final quizService quizservice;
     @Autowired
    private final quizDao quizDao;

    public QuizController(quizService quizService, quizService quizservice, quizDao quizDao) {
        this.quizservice = quizservice;
        this.quizDao = quizDao;
    }

    @PostMapping("/create")
    public ResponseEntity <String >createQuiz(@RequestParam String category, @RequestParam int noOfQuestions, @RequestParam String title){
        return  quizservice.createQuiz(category,noOfQuestions,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(@PathVariable Integer id){
         return quizservice.getQuizQuestion(id);
    }
//    @PostMapping("/submit/{id}")
//    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestParam List<Responce> responce){
//        return  quizservice.calculateResult(id,responce);
//    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Responce> responseList) {
        return quizservice.calculateResult(id, responseList);
    }


}
