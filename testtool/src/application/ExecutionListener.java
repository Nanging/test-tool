package application;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class ExecutionListener extends RunListener {
     ResultRecorder recorder;
     MethodInfo methodInfo;
     List<MethodInfo> list;
     StringBuffer record = new StringBuffer("");
    public ExecutionListener() {
        this.list = new ArrayList<>();
    }

    public void testRunStarted(Description description) throws Exception {
    	record.append("--------- START ----------\n");
        System.out.println("--------- START ----------");
        recorder = new ResultRecorder();
    }

    public void testRunFinished(Result result) throws Exception {
        recorder.setResult(result.wasSuccessful());
        recorder.setList(list);
        System.out.println("--------- END ----------");
        System.out.println("执行结果 : " + result.wasSuccessful());
        System.out.println("执行时间 : " + result.getRunTime());
        System.out.println("执行数量 : " + result.getRunCount());
        System.out.println("失败数量 : " + result.getFailureCount());
        System.out.println("忽略数量 : " + result.getIgnoreCount());
        record.append("--------- END ----------\n");
        record.append("执行结果 : " + result.wasSuccessful()+"\n");
        record.append("执行时间 : " + result.getRunTime()+"\n");
        record.append("执行数量 : " + result.getRunCount()+"\n");
        record.append("失败数量 : " + result.getFailureCount()+"\n");
        record.append("忽略数量 : " + result.getIgnoreCount()+"\n");

    }

    public void testStarted(Description description) throws Exception {
        recorder.setScript_name(description.getClassName());
        record.append("Test Class:"+description.getTestClass().getSimpleName()+" - Test Method:"+description.getMethodName() + " begin"+"\n");
        System.out.println("Test Class:"+description.getTestClass().getSimpleName()+" - Test Method:"+description.getMethodName() + " begin");
        methodInfo = new MethodInfo();
        methodInfo.setMethod_name(description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {
    	record.append("Test Class:"+description.getTestClass().getSimpleName()+" - Test Method:"+description.getMethodName() + " end"+"\n");
        System.out.println("Test Class:"+description.getTestClass().getSimpleName()+" - Test Method:"+description.getMethodName() + " end");
        
        if (methodInfo.getError_msg() == null)
            methodInfo.setResult(true);
        list.add(methodInfo);
    }

    public void testFailure(Failure failure) throws Exception {
    	record.append("Execution of test case failed : " + failure.getMessage()+"\n");
        System.out.println("Execution of test case failed : " + failure.getMessage());
        methodInfo.setResult(false);
        methodInfo.setError_msg(failure.getMessage());
    }

    public void testIgnored(Description description) throws Exception {

    }
    
}
