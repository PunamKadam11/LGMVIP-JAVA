package BasicsProjects.QuizApp.service;

import BasicsProjects.QuizApp.Dao.QuestionDao;
import BasicsProjects.QuizApp.Dao.quizDao;
import BasicsProjects.QuizApp.Entities.QuestionWrapper;
import BasicsProjects.QuizApp.Entities.Questions;
import BasicsProjects.QuizApp.Entities.Quiz;
import BasicsProjects.QuizApp.Entities.Responce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class quizService {
 @Autowired
 private final quizDao quizDao1;
 @Autowired
 private final QuestionDao questionDao;

    public quizService(quizDao quizDao1, QuestionDao questionDao) {
        this.quizDao1 = quizDao1;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
        List<Questions> questions1=questionDao.findRandomQuestionsByCategory(category,noOfQuestions);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions1);
        quizDao1.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>>getQuizQuestion(Integer id) {
    Optional<Quiz> quiz= quizDao1.findById(id);
    List<Questions>quistionFromDB=quiz.get().getQuestions();
    List<QuestionWrapper> questionForUser=new ArrayList<>();
    for(Questions q:quistionFromDB){
        QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
        questionForUser.add(qw);

    }

    return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responce> responseList) {
        Quiz quiz = quizDao1.findById(id).orElse(null);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Questions> questions = quiz.getQuestions();
        int correctResponses = 0;

        for (int i = 0; i < responseList.size() && i < questions.size(); i++) {
            Responce response = responseList.get(i);
            Questions question = questions.get(i);

            if (response.getResponce().equals(question.getRightAnswer())) {
                correctResponses++;
            }
        }

        return new ResponseEntity<>(correctResponses, HttpStatus.OK);
    }

//    public ResponseEntity<Integer> calculateResult(Integer id, List<Responce> responce) {
//        Quiz quiz = quizDao1.findById(id).get();
//        List<Questions> questions = quiz.getQuestions();
//
//        int right = 0;
//        int i = 0;
//        for (Responce responce1 : responce) {
//            if (responce1.getResponce().equals(questions.get(i).getRightAnswer())) {
//                right++;
//                i++;
//            }
//        }
//        return  new ResponseEntity<>(right,HttpStatus.OK);
//    }

}
