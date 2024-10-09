import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestsUnitairesTP2_5.class);
        System.out.println("Tests validés : " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("Tests non validés :");
        for (Failure failure : result.getFailures()) {
            System.out.println("   - " + failure.getDescription() + " : " + failure.getMessage());
        }
    }
}