import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<String> inputToList = Arrays.asList(input.trim().split(","));
        Collection<Integer> collectedInput = new ArrayList<>();

        inputToList.forEach(i -> collectedInput.add(Integer.parseInt(i.trim())));

        return collectedInput.stream().sorted().distinct().collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        List<Integer> arrayInput = new ArrayList<>(input);
        StringBuilder summarizedRangeNumbers = new StringBuilder();

        int firstNumberInRange = arrayInput.get(0);
        int lastNumberInRange = arrayInput.get(0);

        for (int currentNumberInRange : arrayInput) {
            if (currentNumberInRange - lastNumberInRange > 1) {
                if (firstNumberInRange == lastNumberInRange) {
                    summarizedRangeNumbers.append(firstNumberInRange).append(", ");
                } else {
                    summarizedRangeNumbers.append(firstNumberInRange).append("-").append(lastNumberInRange).append(", ");
                }
                firstNumberInRange = currentNumberInRange;
            }
            lastNumberInRange = currentNumberInRange;
        }
        summarizedRangeNumbers.append(lastNumberInRange);
        return summarizedRangeNumbers.toString();
    }

    public static void main(String[] args) {
        NumberRangeSummarizerImpl numberRangeSummarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collect = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String summarizedNumbers = numberRangeSummarizer.summarizeCollection(collect);

        System.out.println("Summarized Numbers " + summarizedNumbers );
    }
}
