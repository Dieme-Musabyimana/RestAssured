package restAssured.exercises.exercise3.task3;

import org.testng.annotations.BeforeMethod;

public class BaseThread {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("STARTING TEST: " + Thread.currentThread().getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
    }
}