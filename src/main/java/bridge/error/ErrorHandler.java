package bridge.error;

import bridge.view.ErrorView;
import java.util.function.Supplier;

public class ErrorHandler {
    private static final ErrorView errorView = new ErrorView();

    public static <T> Object retryUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                errorView.println(e.getMessage());
            }
        }
    }

    public static void retryUntilSuccess(Runnable toRun) {
        while (true) {
            try {
                toRun.run();
                return;
            } catch (IllegalArgumentException e) {
                errorView.println(e.getMessage());
            }
        }
    }
}