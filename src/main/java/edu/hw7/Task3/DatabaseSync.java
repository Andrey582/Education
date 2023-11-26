package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseSync implements PersonDatabase {

    private Map<String, List<Person>> indexName;
    private Map<String, List<Person>> indexAddress;
    private Map<String, List<Person>> indexPhone;
    private Map<Integer, Person> personId;

    public DatabaseSync() {
        indexName = new HashMap<>();
        indexAddress = new HashMap<>();
        indexPhone = new HashMap<>();
        personId = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        addIndex(indexName, person.name(), person);
        addIndex(indexAddress, person.address(), person);
        addIndex(indexPhone, person.phoneNumber(), person);
        personId.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        if (personId.containsKey(id)) {
            Person person = personId.get(id);
            deleteIndex(indexName, person.name(), person);
            deleteIndex(indexAddress, person.address(), person);
            deleteIndex(indexPhone, person.phoneNumber(), person);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return indexName.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return indexAddress.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return indexPhone.get(phone);
    }

    private void addIndex(Map<String, List<Person>> map, String indexType, Person person) {
        map.computeIfAbsent(indexType, e -> new ArrayList<>()).add(person);
    }

    private void deleteIndex(Map<String, List<Person>> map, String indexType, Person person) {

        List<Person> personList = map.get(indexType);

        if (personList != null) {
            map.get(indexType).remove(person);
            if (personList.isEmpty()) {
                map.remove(indexType);
            }
        }
    }
}
