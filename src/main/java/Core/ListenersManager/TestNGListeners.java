package Core.ListenersManager;

import org.testng.*;

public class TestNGListeners implements IExecutionListener , IInvokedMethodListener, ITestListener {
    public void onExecutionStart() {
        // not implemented
    }


    public void onExecutionFinish() {
        // not implemented
    }
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // not implemented
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // not implemented
    }
    public void onTestStart(ITestResult result) {
        // not implemented
    }


    public void onTestSuccess(ITestResult result) {
        // not implemented
    }


    public void onTestFailure(ITestResult result) {
        // not implemented
    }
}
