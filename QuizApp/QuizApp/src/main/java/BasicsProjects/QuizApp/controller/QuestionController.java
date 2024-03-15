package BasicsProjects.QuizApp.controller;

import BasicsProjects.QuizApp.Entities.Questions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BasicsProjects.QuizApp.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
//@SecurityRequirement(name = "auth")
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + "! Welcome to the Java Course";
    }


    @Operation(
            tags = "Get Books",
            description ="getAllBooks",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Data Not Found",
                            responseCode = "404"
                    )
            }
    )

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }


    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Questions questions) {
        return questionService.addQuestion(questions);
    }


    @GetMapping("/getQuestions")
    public ResponseEntity<Page<Questions>> getQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) String category) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        Page<Questions> questions;

        if (category != null) {
            questions = questionService.QustionsByCategory(category,pageable);
        } else {
            questions = questionService.getQuestions(pageable);
        }

        return ResponseEntity.ok(questions);
    }
}
