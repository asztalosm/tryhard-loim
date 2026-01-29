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
                    put("Question", questionData[0]);
                    put("Answer1", questionData[1]);
                    put("Answer2", questionData[2]);
                    put("Answer3", questionData[3]);
                    put("Answer4", questionData[4]);
                    put("Correct", questionData[5]);
                }});
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a kérdéseket tartalmazó fájl");
            //get data from the internet or maybe a failsafe questionnaire embedded in the code
        }
    }



    public static void fillGameQuestions() {
        //todo make a loop for every question
        for (int i = 0; i < 5; i++) { //easy questions

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
