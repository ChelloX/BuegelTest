package test;

import textGenerator.TextGenerator;

import java.util.*;

public class Test {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ICON_SUM = "\u2211\t";
    private static final String TEST_SUCCESS = ANSI_GREEN + "\u2714\t" + ANSI_RESET;
    private static final String TEST_FAILED = ANSI_RED + "\u2716\t" + ANSI_RESET;
    private static final String TEST_EXCEPTION = ANSI_RED + "\u2757\t" + ANSI_RESET;

    private static final String BASE_PATH = "./WoPeD-Process2Text/test/";
    private static SortedMap<String, String> tests = new TreeMap<>();

    static {
        // Key = Dateiname, Value = Erwartetes Ergebnis
        tests.put("AND - (PetriNet Logik).pnml", "<phrase ids=\"t3\"> The process begins, when a customer database is updated. </phrase><phrase ids=\"t2\"> In concurrency to the latter step, the receipt is printed. </phrase>");
        tests.put("AND-Join.pnml", "<phrase ids=\"t1\"> The process begins, when it is checked for the veggie recipe. </phrase><phrase ids=\"t5\"> Then, the ingredients is bought. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the recipe is printed. </phrase>");
        tests.put("AND-Split.pnml", "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t3\"> Then, the veggies is roasted. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the salad is prepared. </phrase>");
        tests.put("AND-Split-Woped.pnml", "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t3\"> Then, the veggies is roasted. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the salad is prepared. </phrase>");
        tests.put("AND-XOR (PetriNet Logik).pnml", "EMPTY");
        tests.put("AND-XOR-Split-Woped.pnml", "<phrase ids=\"t7_op_1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t4\"> Then, the salad is prepared. </phrase><phrase ids=\"t3\"> In concurrency to the latter step, the veggies is roasted. </phrase>");
        tests.put("Mehrfachkombination.pnml", "EMPTY");
        tests.put("Rigid.pnml", "EMPTY");
        tests.put("Schleife-XOR-Split.pnml", "EMPTY");
        tests.put("Sequenz.pnml", "<phrase ids=\"t1\"> The process begins, when a receipt is printed. </phrase><phrase ids=\"t2\"> Then, the receipt is sent. </phrase><phrase ids=\"t3\"> Afterwards, the customer database is updated. </phrase><phrase ids=\"t4\"> Subsequently, the email is sent. </phrase>");
        tests.put("sts.pnml", "<phrase ids=\"t1\"> The process begins, when a text is read. </phrase><phrase ids=\"t3\"> Then, the text is printed. </phrase>");
        tests.put("XOR (PetriNet Logik).pnml", "<phrase ids=\"t5\"> The process begins, when a payment is checked. </phrase><phrase ids=\"p8\"> Then, one of the following branches is executed: </phrase><phrase ids=\"t7\"> The t7 is conducted. </phrase><phrase ids=\"t9\"> Afterwards, the customer is informed. </phrase><phrase ids=\"t6\"> The t6 is conducted. </phrase><phrase ids=\"t8\"> Subsequently, the customer is informed. </phrase>");
        tests.put("XOR-SplitnJoin.pnml", "<phrase ids=\"t1_op_2\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t2\"> Then, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_1\"> Afterwards, the meal is prepared. </phrase>");
        tests.put("XOR-Split.pnml", "<phrase ids=\"t1_op_2\"> The process begins, when it is checked for the recipes. </phrase><phrase ids=\"t2\"> Then, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_1\"> Afterwards, the meal is prepared. </phrase><phrase ids=\"t5\"> Subsequently, the meal is eaten. </phrase>");
    }

    public static void main(String[] args) {
        TextGenerator textGenerator = new TextGenerator();

        int successful = 0;
        int failed = 0;
        int error = 0;

        List<Exception> exceptions = new ArrayList<>();

        System.out.println("Every test will be run up to five times (unless an error occurs) to ensure consistency across different runs.");
        for (Map.Entry<String, String> test : tests.entrySet()) {
            for (int i = 0; i < 5; i++) {
                String expected = removeNewLinesAndSurroundingSpaces("<text>" + test.getValue() + "</text>");
                String result;

                try {
                    result = removeNewLinesAndSurroundingSpaces(textGenerator.toText(BASE_PATH + test.getKey(), true));
                } catch (Exception e) {
                    exceptions.add(e);
                    System.out.println(TEST_EXCEPTION + test.getKey() + " failed with exception: " + e + " (expected: " + expected + ")");
                    error++;
                    break;
                }

                if (result.equals(expected)) {
                    System.out.println(TEST_SUCCESS + test.getKey() + " succeeded.");
                    successful++;
                } else {
                    System.out.println(TEST_FAILED + test.getKey() + " failed.");
                    printDifference(expected, result);
                    failed++;
                    break;
                }
            }
        }

        System.out.println("\n=============================================");
        System.out.println(TEST_SUCCESS + successful + " test successful.");
        System.out.println(TEST_FAILED + failed + " test failed.");
        System.out.println(TEST_EXCEPTION + error + " test failed with exception.");
        System.out.println(ICON_SUM + (successful + failed + error) + " tests executed.");

        System.out.println("\n=============================================");

        if(exceptions.size() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Exception e : exceptions) {
                e.printStackTrace();
            }
        }
    }

    private static String removeNewLinesAndSurroundingSpaces(String input) {
        if (input == null) {
            throw new RuntimeException("input was null");
        }

        return input.replaceAll("\\s*\n\\s*", "");
    }

    private static void printDifference(String expected, String actual) {
        if (expected == null || actual == null) {
            return;
        }

        if (!expected.equals(actual)) {
            System.out.println("\tExpected: " + expected);
            System.out.println("\tActual:   " + actual);
            System.out.print("\t~~~~~~~~~~");
        }

        for (int i = 0; i < expected.length() && i < actual.length(); i++) {
            if (expected.charAt(i) == actual.charAt(i)) {
                System.out.print("~");
            } else {
                System.out.print("^");
            }
        }

        System.out.println("");
    }
}
