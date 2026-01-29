import java.lang.reflect.Array;
import java.util.*;

public class Main {
    //text formatting global variables:
    public static Scanner scn = new Scanner(System.in);
    public static final String TF_RESET = "\u001B[0m";
    public static final String TF_BOLD = "\u001B[1m";
    public static final String TF_UNDERLINE = "\u001B[1m";
    public static final String TF_GREEN = "\u001B[32m";
    public static final String TF_BLUE = "\u001B[34m";
    public static final String TF_RED = "\u001B[31m";
    public static final String TF_YELLOW = "\u001B[33m";
    public static ArrayList<Map<String, String>> easyQuestions = new ArrayList<>();
    public static ArrayList<Map<String, String>> mediumQuestions = new ArrayList<>();
    public static ArrayList<Map<String, String>> hardQuestions = new ArrayList<>();
    public static Map<String, String> map = new HashMap<>();
    public static void exit() {
        scn.close();
        System.exit(0);
    }

    public static void setQuestionDictionary() {
        //Easy Questions
        easyQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        easyQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});

        //Medium Questions
        mediumQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        mediumQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        mediumQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});

        //Hard Questions
        hardQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        hardQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        hardQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
        hardQuestions.add(new HashMap<String, String>() {{
            put("Question", "Question?");
            put("Answer", "Correct Answer");
            put("Incorrect1", "incorrect1");
            put("Incorrect2", "incorrect2");
            put("Incorrect3", "incorrect3");
        }});
    }

    public static void playgame() {
        int points = 0;
        System.out.printf("Könnyű kérdések száma: %d%n", easyQuestions.size());
        System.out.printf("Közepes kérdések száma: %d%n", mediumQuestions.size());
        System.out.printf("Nehéz kérdések száma: %d%n", hardQuestions.size());
    }

    public static void menu() {
        System.out.printf("%s\tLegyen ön is Milliomos v0.1%n%s", (TF_BOLD + TF_BLUE), (TF_RESET));
        System.out.println("[1] Játék kezdése");
        System.out.println("[2] Kilépés"); //maybe beállítások
        setQuestionDictionary();
        String menuUserOption = null;
        do {
            menuUserOption = String.valueOf(scn.nextLine().charAt(0));
        } while (!menuUserOption.equals("1") && !menuUserOption.equals("2"));
        if (menuUserOption.equals("1")) {
            playgame();
        } else {
            exit();
        }
    }

    public static void main(String[] args) {
        menu();

    }
}
