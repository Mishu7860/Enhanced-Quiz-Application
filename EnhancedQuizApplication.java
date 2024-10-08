import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class EnhancedQuizApplication {
    // Quiz Questions, Options, and Answers
    static String[][] questions = {
        {"What is the capital of France?", "A) Berlin", "B) Madrid", "C) Paris", "D) Rome", "C"},
        {"Which planet is known as the Red Planet?", "A) Earth", "B) Mars", "C) Jupiter", "D) Saturn", "B"},
        {"What is the largest ocean on Earth?", "A) Atlantic", "B) Indian", "C) Arctic", "D) Pacific", "D"},
        {"Who wrote 'Romeo and Juliet'?", "A) Charles Dickens", "B) Mark Twain", "C) William Shakespeare", "D) Jane Austen", "C"},
        {"What is the chemical symbol for water?", "A) H2O", "B) O2", "C) CO2", "D) NaCl", "A"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0; // To keep track of the user's score
        int totalQuestions = questions.length;
        int correctAnswers = 0; // Count of correct answers
        int incorrectAnswers = 0; // Count of incorrect answers

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have 10 seconds to answer each question.\n");

        for (int i = 0; i < totalQuestions; i++) {
            String[] questionData = questions[i];
            System.out.println("Question " + (i + 1) + ": " + questionData[0]);
            System.out.println(questionData[1]);
            System.out.println(questionData[2]);
            System.out.println(questionData[3]);
            System.out.println(questionData[4]);

            String userAnswer = getUserAnswer(scanner, 10); // Get user's answer with a timer

            // Check if the answer is correct
            if (userAnswer != null && userAnswer.equalsIgnoreCase(questionData[5])) {
                System.out.println("Correct!\n");
                score++;
                correctAnswers++; // Increment count of correct answers
            } else if (userAnswer != null) {
                System.out.println("Incorrect! The correct answer is " + questionData[5] + ".\n");
                incorrectAnswers++; // Increment count of incorrect answers
            } else {
                System.out.println("Time is up! The correct answer is " + questionData[5] + ".\n");
                incorrectAnswers++; // Increment count of incorrect answers if time runs out
            }
        }

        // Display Result Screen
        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + totalQuestions);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
        scanner.close();
    }

    private static String getUserAnswer(Scanner scanner, int seconds) {
        final String[] answer = {null}; // To store user's answer
        Timer timer = new Timer();

        // Timer task to stop input after a certain time
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime is up!");
                // Stop the scanner input
                if (answer[0] == null) {
                    answer[0] = "TIME_UP"; // Mark as timeout
                }
            }
        }, seconds * 1000);

        // Ask for user input
        System.out.print("Your answer (A/B/C/D): ");
        answer[0] = scanner.nextLine();

        // Cancel the timer if the user answers in time
        timer.cancel();

        return answer[0];
    }
}
