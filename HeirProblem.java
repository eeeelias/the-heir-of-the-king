package TheHeir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class HeirProblem {
    private int nbrOfFamilies, nbrOfClaimers;
    private String king;
    private Scanner scanner;
    private Person theKing;
    private Person heir;

    private HashMap<String,Person> allPersons = new HashMap<>();
    //Only for debugging
    private List<Person> listOfAll = new ArrayList<>();

    public HeirProblem(int nbrOfFamilies, int nbrOfClaimers, String king, Scanner scanner){
        this.scanner = scanner;
        this.nbrOfFamilies = nbrOfFamilies;
        this.nbrOfClaimers = nbrOfClaimers;
        this.king = king;
        theKing = new Person(king);
        theKing.setRoyalValue(100);
        allPersons.put(king,theKing);

        start();
    }

    public void start(){
        for(int i=0; i<nbrOfFamilies;i++){
            Person child = null;
            Person parent;
            for(int j=0; j<3; j++){
                String name = scanner.next();
                if(j==0){
                    if(allPersons.containsKey(name))
                        child = allPersons.get(name);
                    else
                        child = new Person(name);
                } else {
                    if(allPersons.containsKey(name))
                        parent = allPersons.get(name);
                    else
                        parent = new Person(name);
                    //parents.add(parent);
                    child.getParents().add(parent);

                    parent.getChildrens().add(child);
                    allPersons.put(parent.getName(), parent);
                }
            }

            allPersons.put(child.getName(), child);
        }


        Person tempKing = allPersons.get(king);
        setupHierarchyBloodValue(tempKing, 1);
        decideTheHeir();

        System.out.println(heir.getName());

    }

    //private Person tempPerson;

    public void setupHierarchyBloodValue(Person person, double steps){
        List<Person> childrens = person.getChildrens();
        steps = steps*2;

        if(childrens.isEmpty()){
            return;
        }

        for(Person child : childrens){
            Person tempPerson = allPersons.get(child.getName());
            double oldValue = tempPerson.getRoyalValue();
            double newValue = oldValue + (100/steps);
            tempPerson.setRoyalValue(newValue);
            allPersons.put(tempPerson.getName(),tempPerson);
            setupHierarchyBloodValue(child, steps);

        }


    }

    public void decideTheHeir(){
        for(int i=0; i < nbrOfClaimers; i++){
            String nameOfClaimer = scanner.next();
            //System.out.println(nameOfClaimer);
            if(allPersons.containsKey(nameOfClaimer)){
                Person claimer = allPersons.get(nameOfClaimer);
                System.out.println("claimer: " + claimer.getName() + " has value: " + claimer.getRoyalValue());
                if(heir == null){
                    heir = claimer;
                } else {
                    if(heir.getRoyalValue() < claimer.getRoyalValue()){
                        heir = claimer;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);

        int nbrOfFamilies = scanner.nextInt();
        int nbrOfClaimers = scanner.nextInt();
        String king = scanner.next();

        HeirProblem hp = new HeirProblem(nbrOfFamilies,nbrOfClaimers,king,scanner);
    }
}
