// 代码生成时间: 2025-09-30 01:51:29
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// Entity class for Question
class Question {
    private int id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctAnswer;

    // Constructor, getters, and setters
    public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD, char correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Getters and setters for each field
    // ... (omitted for brevity)
}

// Entity class for Exam
class Exam {
    private int id;
    private String name;
    private List<Question> questions;

    // Constructor, getters, and setters
    public Exam(int id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    // Getters and setters for each field
    // ... (omitted for brevity)
}

// Service class for managing exams and questions
class ExamService {
    public void addExam(Exam exam) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(exam);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Exam getExam(int id) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Exam exam = null;
        try {
            exam = session.get(Exam.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return exam;
    }

    // Other methods for managing questions and exams
    // ... (omitted for brevity)
}

// Main class to run the application
public class OnlineExamSystem {
    public static void main(String[] args) {
        ExamService examService = new ExamService();

        // Create exam with questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "What is the capital of France?", "A. Paris", "B. London", "C. Berlin", "D. Madrid", 'A'));
        questions.add(new Question(2, "What is the largest planet in our solar system?", "A. Earth", "B. Mars", "C. Jupiter", "D. Saturn", 'C'));

        Exam exam = new Exam(1, "General Knowledge", questions);

        // Add exam to the database
        examService.addExam(exam);

        // Retrieve exam from the database
        Exam retrievedExam = examService.getExam(1);
        if (retrievedExam != null) {
            System.out.println("Exam Name: " + retrievedExam.getName());
            // Print questions
            for (Question question : retrievedExam.getQuestions()) {
                System.out.println("Question: " + question.getQuestionText());
                System.out.println("Options: " + question.getOptionA() + ", " + question.getOptionB() + ", " + question.getOptionC() + ", " + question.getOptionD());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
                System.out.println();
            }
        } else {
            System.out.println("Exam not found.");
        }
    }
}