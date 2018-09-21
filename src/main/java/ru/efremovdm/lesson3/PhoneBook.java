package ru.efremovdm.lesson3;

import java.util.*;

/**
 * Телефонный справочник.
 * Для хранения используется TreeSet чтобы получать телефоны в порядке их добавления.
 */
public class PhoneBook {

    private Map<String, TreeSet> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    /**
     * Добавление телефона в справочник
     *
     * @param surname
     * @param phone
     */
    public void add(String surname, String phone) {

        TreeSet<String> numbers = contacts.containsKey(surname) ? contacts.get(surname) : new TreeSet<>();

        numbers.add(phone);

        contacts.put(surname, numbers);
    }

    /**
     * Получение списка телефонов по фамилии
     *
     * @param surname
     * @return
     */
    public Set<String> get(String surname) {
        return contacts.get(surname);
    }
}
