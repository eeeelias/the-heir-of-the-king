package TheHeir;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double royalValue;
    private List<Person> childrens = new ArrayList<>();
    private List<Person> parents = new ArrayList<>();

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRoyalValue() {
        return royalValue;
    }

    public void setRoyalValue(double royalValue) {
        this.royalValue = royalValue;
    }

    public List<Person> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Person> childrens) {
        this.childrens = childrens;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }
}
