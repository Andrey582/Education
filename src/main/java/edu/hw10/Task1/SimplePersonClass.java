package edu.hw10.Task1;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;

public class SimplePersonClass {

    @Min(10)
    @Max(30)
    @NotNull
    private Integer age;
    @NotNull
    private String name;

    public static SimplePersonClass create() {
        return new SimplePersonClass();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private SimplePersonClass() {
    }

    public SimplePersonClass(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}
