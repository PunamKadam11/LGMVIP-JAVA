package BasicsProjects.QuizApp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Responce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String responce;
    public Responce(int value) {

    }

}
