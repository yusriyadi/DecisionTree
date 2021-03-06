import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ArrayList<Boolean> typeSpecification = new ArrayList<>(Arrays.asList(true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false));
        ArrayList<Boolean> chosenAttributes = new ArrayList<>(Arrays.asList(false, true, false, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true));

        DecisionTree dt = new DecisionTree(typeSpecification, chosenAttributes, ";", false);

        List<String[]> entries = dt.readCSV("smallerData.csv", true);

        int trainSize = (int) (entries.size() * 0.8);

        ArrayList<String[]> trainEntries = new ArrayList<>();

        ArrayList<String[]> testEntries = new ArrayList<>();

        List<Integer> testIndex = new ArrayList<>();

        for (int j = 0; j < entries.size() - trainSize; j ++) {
            Integer index = (int) (Math.random() * (entries.size()));
            while (testIndex.contains(index)) {
                index = (int) (Math.random() * (entries.size()));
            }
            testIndex.add(index);
            testEntries.add(entries.get(index));
        }
        for (int i = 0; i < entries.size(); i ++) {
            if (testIndex.contains(new Integer(i))) {
                continue;
            }
            trainEntries.add(entries.get(i));
        }

        entries = null;

        dt.loadData(true, trainEntries);

        dt.loadData(false, testEntries);

        dt.startTraining();

        dt.preorderTraversePrint(dt.start, dt.root, -1, false, true);

        dt.startTesting();

        dt.confusionMatrixPrint();

    }
}
