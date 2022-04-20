import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {
        List<String> names = Arrays.asList("Jack", "Tommy", "Anna", "Mark", "Sam", "Fil", "Gera", "Dan", "Serge");
        List<String> families = Arrays.asList("Smith", "Clark", "Wilson", "Young", "Brown", "Mekler");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        System.out.println("Count child: " +
                persons.stream()
                        .filter(person -> person.getAge() < 18)
                        .count());
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))
                .map(Person::getFamily)
                .collect(Collectors.toList()); //спецально не стал использовать подсказку IDEA (toList())
        System.out.println("\nConscripts:");
        for (String conscript : conscripts) {
            System.out.println(conscript);
        }
        List<Person> workingPeople = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN ?
                        (person.getAge() >= 18 && person.getAge() <= 65) : (person.getAge() >= 18 && person.getAge() <= 6))
                .toList(); //воспользовался подсказкой IDEA, что бы понять что работает как и collect(Collectors.toList())
        System.out.println("\nWorking people:");
        for (Person person : workingPeople) {
            System.out.println(person);
        }
    }
}