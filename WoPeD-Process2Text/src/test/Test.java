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
    private static final SortedMap<String, List<String>> tests = new TreeMap<>();

    static {
        // Key = Dateiname, Value = Erwartetes Ergebnis
        tests.put("AND - (PetriNet Logik).pnml", Arrays.asList(
                "<phrase ids=\"t3\"> The process begins, when a customer database is updated. </phrase><phrase ids=\"t2\"> In concurrency to the latter step, the receipt is printed. </phrase>",
                "<phrase ids=\"t2\"> The process begins, when a receipt is printed. </phrase><phrase ids=\"t3\"> In concurrency to the latter step, the customer database is updated. </phrase>"
        ));
        tests.put("AND-Join.pnml", Arrays.asList(
                "<phrase ids=\"t1\"> The process begins, when it is checked for the veggie recipe. </phrase><phrase ids=\"t5\"> Then, the ingredients is bought. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the recipe is printed. </phrase>",
                "<phrase ids=\"t1\"> The process begins, when it is checked for the veggie recipe. </phrase><phrase ids=\"t4\"> Then, the recipe is printed. </phrase><phrase ids=\"t5\"> In concurrency to the latter step, the ingredients is bought. </phrase>"
        ));
        tests.put("AND-Split.pnml", Arrays.asList(
                "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t3\"> Then, the veggies is roasted. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the salad is prepared. </phrase>",
                "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t4\"> Then, the salad is prepared. </phrase><phrase ids=\"t3\"> In concurrency to the latter step, the veggies is roasted. </phrase>"
        ));
        tests.put("AND-Split-Woped.pnml", Arrays.asList(
                "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t3\"> Then, the veggies is roasted. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the salad is prepared. </phrase>",
                "<phrase ids=\"t1\"> The process begins, when it is checked for the recepies. </phrase><phrase ids=\"t4\"> Then, the salad is prepared. </phrase><phrase ids=\"t3\"> In concurrency to the latter step, the veggies is roasted. </phrase>"
        ));
        tests.put("AND-XOR (PetriNet Logik).pnml", Arrays.asList(
        ));
        tests.put("AND-XOR-Split-Woped.pnml", Arrays.asList(
                "<phrase ids=\"p1\"> One of the following branches is executed. </phrase><phrase ids=\"t7_op_2\"> Then, the process begins when it is checked for the recepies. </phrase><phrase ids=\"t6\"> Afterwards, the needed ingredients is gotten. </phrase><phrase ids=\"t9\"> Subsequently, the steak is grilled. </phrase><phrase ids=\"t1_op_3\"> Then, the t1 is conducted. </phrase><phrase ids=\"t7_op_1\"> It is checked for the recepies. </phrase><phrase ids=\"t3\"> Afterwards, the veggies is roasted. </phrase><phrase ids=\"t4\"> In concurrency to the latter step, the salad is prepared. </phrase><phrase ids=\"t1_op_4,t8_op_1\"> Once both branches were finished, the t1 is conducted. </phrase><phrase ids=\"null,p1\"> Once one of the alternative branches was executed it is finished. </phrase>",
                "<phrase ids=\"p1\"> One of the following branches is executed. </phrase><phrase ids=\"t7_op_2\"> Then, the process begins when it is checked for the recepies. </phrase><phrase ids=\"t6\"> Afterwards, the needed ingredients is gotten. </phrase><phrase ids=\"t9\"> Subsequently, the steak is grilled. </phrase><phrase ids=\"t1_op_3\"> Then, the t1 is conducted. </phrase><phrase ids=\"t7_op_1\"> It is checked for the recepies. </phrase><phrase ids=\"t4\"> Afterwards, the salad is prepared. </phrase><phrase ids=\"t3\"> In concurrency to the latter step, the veggies is roasted. </phrase><phrase ids=\"t1_op_4,t8_op_1\"> Once both branches were finished, the t1 is conducted. </phrase><phrase ids=\"null,p1\"> Once one of the alternative branches was executed it is finished. </phrase>"
        ));
        tests.put("Mehrfachkombination.pnml", Arrays.asList(
        ));
        tests.put("Rigid.pnml", Arrays.asList(
        ));
        tests.put("Schleife-XOR-Split.pnml", Arrays.asList(
        ));
        tests.put("Sequenz.pnml", Arrays.asList(
                "<phrase ids=\"t1\"> The process begins, when a receipt is printed. </phrase><phrase ids=\"t2\"> Then, the receipt is sent. </phrase><phrase ids=\"t3\"> Afterwards, the customer database is updated. </phrase><phrase ids=\"t4\"> Subsequently, the email is sent. </phrase>"
        ));
        tests.put("sts.pnml", Arrays.asList(
                "<phrase ids=\"t1\"> The process begins, when a text is read. </phrase><phrase ids=\"t3\"> Then, the text is printed. </phrase>"
        ));
        tests.put("XOR (PetriNet Logik).pnml", Arrays.asList(
                "<phrase ids=\"t5\"> The process begins, when a payment is checked. </phrase><phrase ids=\"p8\"> Then, one of the following branches is executed: </phrase><phrase ids=\"t7\"> The t7 is conducted. </phrase><phrase ids=\"t9\"> Afterwards, the customer is informed. </phrase><phrase ids=\"t6\"> The t6 is conducted. </phrase><phrase ids=\"t8\"> Subsequently, the customer is informed. </phrase><phrase ids=\"null,p8\"> Once one of the alternative branches was executed it is finished. </phrase>",
                "<phrase ids=\"t5\"> The process begins, when a payment is checked. </phrase><phrase ids=\"p8\"> Then, one of the following branches is executed: </phrase><phrase ids=\"t6\"> The t6 is conducted. </phrase><phrase ids=\"t8\"> Afterwards, the customer is informed. </phrase><phrase ids=\"t7\"> The t7 is conducted. </phrase><phrase ids=\"t9\"> Subsequently, the customer is informed. </phrase><phrase ids=\"null,p8\"> Once one of the alternative branches was executed it is finished. </phrase>"
        ));
        tests.put("XOR-SplitnJoin.pnml", Arrays.asList(
                "<phrase ids=\"p1\"> One of the following branches is executed. </phrase><phrase ids=\"t1_op_2\"> Then, the process begins when it is checked for the recepies. </phrase><phrase ids=\"t2\"> Afterwards, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_1\"> Subsequently, the meal is prepared. </phrase><phrase ids=\"t1_op_1\"> It is checked for the recepies. </phrase><phrase ids=\"t3\"> Then, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_2\"> Afterwards, the meal is prepared. </phrase><phrase ids=\"null,p1\"> Once one of the alternative branches was executed it is finished. </phrase>",
                "<phrase ids=\"p1\"> One of the following branches is executed. </phrase><phrase ids=\"t1_op_1\"> Then, the process begins when it is checked for the recepies. </phrase><phrase ids=\"t3\"> Afterwards, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_2\"> Subsequently, the meal is prepared. </phrase><phrase ids=\"t1_op_2\"> It is checked for the recepies. </phrase><phrase ids=\"t2\"> Then, the needed ingredients is gotten. </phrase><phrase ids=\"t4_op_1\"> Afterwards, the meal is prepared. </phrase><phrase ids=\"null,p1\"> Once one of the alternative branches was executed it is finished. </phrase>"
        ));
    }

    public static void main(String[] args) {
        TextGenerator textGenerator = new TextGenerator();

        int successful = 0;
        int failed = 0;
        int error = 0;

        List<Exception> exceptions = new ArrayList<>();

        for (Map.Entry<String, List<String>> test : tests.entrySet()) {
            List<String> expected = test.getValue();
            String result;

            try {
                result = removeNewLinesAndSurroundingSpaces(textGenerator.toText(BASE_PATH + test.getKey(), true));
                result = result.substring(0, result.length() - 7).substring(6);
            } catch (Exception e) {
                exceptions.add(e);
                System.out.println(TEST_EXCEPTION + test.getKey() + " failed with exception: " + e + " (expected: " + expected + ")");
                error++;
                continue;
            }

            if(result.isEmpty()) {
                System.out.println(TEST_FAILED + test.getKey() + " failed with no result.");
                failed++;
                continue;
            }

            if (!equals(result, expected)) {
                System.out.println(TEST_FAILED + test.getKey() + " failed.");
                printDifference(expected, result);
                failed++;
                continue;
            }

            successful++;
            System.out.println(TEST_SUCCESS + test.getKey() + " succeeded.");
        }

        System.out.println("\n=============================================");
        System.out.println(TEST_SUCCESS + successful + " test successful.");
        System.out.println(TEST_FAILED + failed + " test failed.");
        System.out.println(TEST_EXCEPTION + error + " test failed with exception.");
        System.out.println(ICON_SUM + (successful + failed + error) + " tests executed.");

        System.out.println("\n=============================================");

        if (exceptions.size() > 0) {
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

    private static boolean equals(String result, List<String> control) {
        for(String c : control) {
            if(result.equals(removeNewLinesAndSurroundingSpaces(c))) {
                return true;
            }
        }
        return false;
    }

    private static String removeNewLinesAndSurroundingSpaces(String input) {
        if (input == null) {
            throw new RuntimeException("input was null");
        }

        return input.replaceAll("\\s*\n\\s*", "");
    }

    private static void printDifference(List<String> expected, String actual) {
        if (expected == null || actual == null) {
            return;
        }

        System.out.println("\tExpected one of: ");
        for(String s : expected) {
            System.out.println("\t\t\t- " + s);
        }
        System.out.println("\tBut got:  " + actual);
    }
}
