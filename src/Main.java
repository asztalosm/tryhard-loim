import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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


    public static void setQuestionsFromTxts() {
        //feltölt egy arraylistet kérdésekkel és válaszokkal, arraylistből majd törölni fogunk
        File easyQuestionsFile = new File("src/easyQuestions");
        File mediumQuestionsFile = new File("src/mediumQuestions");
        File hardQuestionsFile = new File("src/hardQuestions");
        try (Scanner fileReader = new Scanner(easyQuestionsFile)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] questionData = data.split("><"); //we split like this so if we were to write a code snippet the data won't get fucked
                easyQuestions.add(new HashMap<>() {{
                    put("Question", (TF_YELLOW + questionData[0] + TF_RESET));
                    put("CorrectAnswer", questionData[1]);
                    put("Answer2", questionData[2]);
                    put("Answer3", questionData[3]);
                    put("Answer4", questionData[4]);
                    put("Correct", questionData[5]);
                }});
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a kérdéseket tartalmazó fájl"); //get data from the internet or maybe a failsafe questionnaire embedded in the code
        }
        //n i guess we'll do this for all arrays, pretty ugly code but i cant be arsed
        try (Scanner fileReader = new Scanner(mediumQuestionsFile)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] questionData = data.split("><");
                mediumQuestions.add(new HashMap<>() {{
                    put("Question", (TF_YELLOW + questionData[0] + TF_RESET));
                    put("CorrectAnswer", questionData[1]);
                    put("Answer2", questionData[2]);
                    put("Answer3", questionData[3]);
                    put("Answer4", questionData[4]);
                    put("Correct", questionData[5]);
                }});
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a kérdéseket tartalmazó fájl");
        }
        try (Scanner fileReader = new Scanner(hardQuestionsFile)) {
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] questionData = data.split("><");
                hardQuestions.add(new HashMap<>() {{
                    put("Question", (TF_YELLOW + questionData[0] + TF_RESET));
                    put("CorrectAnswer", questionData[1]);
                    put("Answer2", questionData[2]);
                    put("Answer3", questionData[3]);
                    put("Answer4", questionData[4]);
                    put("Correct", questionData[5]);
                }});
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a kérdéseket tartalmazó fájl");
        }
    }


    public static void fillGameQuestions() {
        for (int i = 0; i < 5; i++) { //ja ezt valszeg jobb lenne úgy megcsinálni, hogy hozzáadunk, majd kitöröljük a listából. namindegy, majd fixelem
            int randomInt = (int) (Math.random() * easyQuestions.size());
            while (gameQuestions.contains(easyQuestions.get(randomInt))) {
                randomInt = (int) (Math.random() * easyQuestions.size());
            }
            gameQuestions.add(easyQuestions.get(randomInt));
        }
        for (int i = 0; i < 5; i++) {
            int randomInt = (int) (Math.random() * mediumQuestions.size());
            while (gameQuestions.contains(mediumQuestions.get(randomInt))) {
                randomInt = (int) (Math.random() * mediumQuestions.size());
            }
            gameQuestions.add(mediumQuestions.get(randomInt));
        }
        for (int i = 0; i < 5; i++) {
            int randomInt = (int) (Math.random() * hardQuestions.size());
            while (gameQuestions.contains(hardQuestions.get(randomInt))) {
                randomInt = (int) (Math.random() * hardQuestions.size());
            }
            gameQuestions.add(hardQuestions.get(randomInt));
        }
        mainGame();
    }

    public static void mainGame() {
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
                gameQuestions.clear();
                easyQuestions.clear();
                menu();
                return;
            }
        }
    }


    public static void playgame() {
        int points = 0;
        System.out.printf("Könnyű kérdések száma: %d%n", easyQuestions.size());
        System.out.printf("Közepes kérdések száma: %d%n", mediumQuestions.size());
        System.out.printf("Nehéz kérdések száma: %d%n", hardQuestions.size());
        fillGameQuestions();

    }

    public static void menu() {
        System.out.printf("%s\tLegyen ön is Milliomos v0.1%n%s", (TF_BOLD + TF_BLUE), (TF_RESET));
        System.out.println("[1] Játék kezdése");
        System.out.println("[2] Kilépés"); //maybe beállítások
        setQuestionsFromTxts();
        //setQuestionDictionary();
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

    public static void main(String[] args) {
        menu();

    }
}
