package restAssured.exercises.utils;

import com.github.javafaker.Faker;

public class FakeUtils {
    public String generateName(){
        Faker faker = new Faker();
        return "Email" + faker.regexify("[A-za-z0-9 ,_-]{10}");
    }
    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("[A-Za-z0-9_@./#&+-]{50}");
    }
}

