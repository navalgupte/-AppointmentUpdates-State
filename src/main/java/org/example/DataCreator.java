package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataCreator {
    private static final String DEFAULT_FILE = "data.ser";
    private static Calendar dateCreator = Calendar.getInstance();

    public static void main(String [] args) {
        String fileName;
        if(args.length == 1) {
            fileName = args[0];
        } else {
            fileName = DEFAULT_FILE;
        }
        serialize(fileName);
    }

    public static void serialize(String fileName) {
        try {
            serializeToFile(createData(), fileName);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private static Serializable createData() {
        ArrayList appointments = new ArrayList();
        ArrayList contacts = new ArrayList();

        contacts.add(new ContactImpl("Test", "Subject", "Volunteer", "Patterns Consortium"));
        Location location = new LocationImpl("San Francisco, CA");
        appointments.add(new Appointment("Java focus group", contacts, location, createDate(2024, 8, 1, 12, 30), createDate(2024, 8, 2, 17, 30)));
        appointments.add(new Appointment("DS focus group", contacts, location, createDate(2024, 8, 3, 12, 30), createDate(2024, 8, 5, 17, 30)));
        appointments.add(new Appointment("Algorithms focus group", contacts, location, createDate(2024, 8, 8, 12, 30), createDate(2024, 8, 9, 17, 30)));
        return appointments;
    }

    private static void serializeToFile(Serializable content, String fileName) throws IOException {
        ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName));
        serOut.writeObject(content);
        serOut.close();
    }

    public static Date createDate(int year, int month, int day, int hour, int minute) {
        dateCreator.set(year, month, day, hour, minute);
        return dateCreator.getTime();
    }
}
