import java.io.*;
import java.util.*;
import java.net.*;

public class Main {
    //text formatting global variables:
    public static Scanner scn = new Scanner(System.in);
    public static final String TF_RESET = "\u001B[0m";
    public static final String TF_BOLD = "\u001B[1m";
    public static final String TF_GREEN = "\u001B[32m";
    public static final String TF_BLUE = "\u001B[34m";
    public static final String TF_RED = "\u001B[31m";
    public static final String TF_YELLOW = "\u001B[33m";
    public static ArrayList<Map<String, String>> easyQuestions = new ArrayList<>();
    public static ArrayList<Map<String, String>> mediumQuestions = new ArrayList<>();
    public static ArrayList<Map<String, String>> hardQuestions = new ArrayList<>();
    public static ArrayList<Map<String, String>> gameQuestions = new ArrayList<>();
    int winnings = 0;

    public static void loadQuestions(ArrayList<Map<String, String>> questionList, String source, boolean fromInternet) throws IOException {
        BufferedReader br = null;
        if (fromInternet) {
            br = new BufferedReader(new InputStreamReader(new URL(source).openStream()));
        }
        else if (!fromInternet) {
            br = new BufferedReader(new FileReader(source));
        }
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split("><");
            Map<String, String> question = new HashMap<>();
            question.put("Question", TF_YELLOW + data[0] + TF_RESET);
            question.put("CorrectAnswer", data[1]);
            question.put("Answer2", data[2]);
            question.put("Answer3", data[3]);
            question.put("Answer4", data[4]);
            question.put("Correct", data[5]);
            questionList.add(question);
        }
        br.close();
    }

    public static void setQuestions() throws IOException { //self explanatory
        File easyQuestionsFile = new File("src/easyQuestions");
        File mediumQuestionsFile = new File("src/mediumQuestions");
        File hardQuestionsFile = new File("src/hardQuestions");

        if (easyQuestionsFile.exists() && mediumQuestionsFile.exists() && hardQuestionsFile.exists()) {
            loadQuestions(easyQuestions, "src/easyQuestions", false);
            loadQuestions(mediumQuestions, "src/mediumQuestions", false);
            loadQuestions(hardQuestions, "src/hardQuestions", false);
        }
        else {
            loadQuestions(easyQuestions, "https://raw.githubusercontent.com/kriszhadvicefia/lomlQuestions/refs/heads/main/easyQuestions", true);
            loadQuestions(mediumQuestions, "https://raw.githubusercontent.com/kriszhadvicefia/lomlQuestions/refs/heads/main/mediumQuestions", true);
            loadQuestions(hardQuestions, "https://raw.githubusercontent.com/kriszhadvicefia/lomlQuestions/refs/heads/main/hardQuestions", true);
        }

    }

    public static Map<String, String> getRandomArrayItem(ArrayList<Map<String, String>> arraylist) {
        int arraySize = arraylist.size();
        int randomNumber = (int) (Math.random() * arraySize);
        Map<String, String> arrayValue = arraylist.get(randomNumber);
        arraylist.remove(randomNumber);
        return arrayValue;
    }

    public static void fillGameQuestions() throws IOException {
        for (int i = 0; i < 5; i++) {
            gameQuestions.add(getRandomArrayItem(easyQuestions));
        }
        for (int i = 0; i < 5; i++) {
            gameQuestions.add(getRandomArrayItem(mediumQuestions));
        }
        for (int i = 0; i < 5; i++) {
            gameQuestions.add(getRandomArrayItem(hardQuestions));
        }
        mainGame();
    }

    public static void mainGame() throws IOException {
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        ArrayList<String> answers = new ArrayList<>();
        String correctAnswer;

        for (int i = 0; i < gameQuestions.size(); i++) {
            answers.clear();
            correctAnswer = "";

            System.out.println((i + 1) + ". kérdés: " + gameQuestions.get(i).get("Question"));

            for (String key : gameQuestions.get(i).keySet()) {
                if (!key.equals("Correct") && !key.equals("Question")) {
                    answers.add(gameQuestions.get(i).get(key));
                }
                if (key.equals("CorrectAnswer")) {
                    correctAnswer = gameQuestions.get(i).get(key);
                }
            }

            Collections.shuffle(answers);

            for (int j = 0; j < answers.size(); j++) {
                System.out.println(letters.get(j) + ". " + answers.get(j));
            }

            System.out.print("Melyik a megoldás? ");
            String userInput = scn.nextLine().toUpperCase();
            int chosenIndex = letters.indexOf(userInput);

            if (chosenIndex == -1) {
                System.out.println("Érvénytelen válasz! Próbáld újra.");
                i--;
                continue;
            }

            if (answers.get(chosenIndex).equals(correctAnswer)) {
                System.out.println(TF_GREEN + "Helyes!" + TF_RESET);
            } else {
                System.out.println(TF_RED + "Vesztettél! A helyes válasz: " + correctAnswer + TF_RESET);
                menu();
                return;
            }
        }
    }

    public static void playgame() throws IOException {
        fillGameQuestions();

    }

    public static void menu() throws IOException {
        System.out.printf("%s\tLegyen ön is Milliomos v0.1%n%s", (TF_BOLD + TF_BLUE), (TF_RESET));
        System.out.println("[1] Játék kezdése");
        System.out.println("[2] Kilépés"); //maybe beállítások
        setQuestions();
        String menuUserOption;
        do {
            menuUserOption = String.valueOf(scn.nextLine().charAt(0));
        } while (!menuUserOption.equals("1") && !menuUserOption.equals("2"));
        if (menuUserOption.equals("1")) {
            playgame();
        } else {
            scn.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        menu();
    }
}
