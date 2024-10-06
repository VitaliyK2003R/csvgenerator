package ru.komissarov.application;

import ru.komissarov.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Hello", null);
        Person p2 = new Person("Hello", "World");
        Person p3 = new Person("lastName", "\"last,Name\"");
        Reformer reformer = new CommaSeparatorReformer();
        FieldParser parser = new CSVFieldParser(reformer);
        CSVWriter writer = new AnnotationCSVWriter(parser, "./");
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        writer.writeData(list, "Person");
    }
}