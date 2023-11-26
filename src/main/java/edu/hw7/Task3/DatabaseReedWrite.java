package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseReedWrite implements PersonDatabase {

    private Map<String, List<Person>> indexName;
    private Map<String, List<Person>> indexAddress;
    private Map<String, List<Person>> indexPhone;
    private Map<Integer, Person> personId;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public DatabaseReedWrite() {
        indexName = new HashMap<>();
        indexAddress = new HashMap<>();
        indexPhone = new HashMap<>();
        personId = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        rwLock.writeLock().lock();
        addIndex(indexName, person.name(), person);
        addIndex(indexAddress, person.address(), person);
        addIndex(indexPhone, person.phoneNumber(), person);
        personId.put(person.id(), person);
        rwLock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        rwLock.writeLock().lock();
        if (personId.containsKey(id)) {
            Person person = personId.get(id);
            deleteIndex(indexName, person.name(), person);
            deleteIndex(indexAddress, person.address(), person);
            deleteIndex(indexPhone, person.phoneNumber(), person);
        }
        rwLock.writeLock().unlock();
    }

    @Override
    public List<Person> findByName(String name) {
        rwLock.readLock().lock();
        List<Person> personList = indexName.get(name);
        rwLock.readLock().unlock();
        return personList;
    }

    @Override
    public List<Person> findByAddress(String address) {
        rwLock.readLock().lock();
        List<Person> personList = indexAddress.get(address);
        rwLock.readLock().unlock();
        return personList;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        rwLock.readLock().lock();
        List<Person> personList = indexPhone.get(phone);
        rwLock.readLock().unlock();
        return personList;
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
