package edu.project3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private final Map<String, Integer> address;
    private final Map<String, Integer> answerCodes;
    private final Map<LocalDate, Integer> dates;
    private final Map<String, Integer> resources;
    private long requestCount = 0;
    private long requestSize = 0;
    private List<String> files;

    public Storage() {
        address = new HashMap<>();
        answerCodes = new HashMap<>();
        dates = new HashMap<>();
        resources = new HashMap<>();
    }

    public void addAddress(String address) {
        if (this.address.containsKey(address)) {
            this.address.put(address, this.address.get(address) + 1);
        } else {
            this.address.put(address, 1);
        }
    }

    public List<Map.Entry<String, Integer>> getAddress() {
        return List.copyOf(
            address.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList().reversed());
    }

    public void addAnswerCode(String code) {
        if (answerCodes.containsKey(code)) {
            answerCodes.put(code, answerCodes.get(code) + 1);
        } else {
            answerCodes.put(code, 1);
        }
    }

    public List<Map.Entry<String, Integer>> getAnswerCode() {
        return List.copyOf(
            answerCodes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList().reversed());
    }

    public void addDate(LocalDate date) {
        if (dates.containsKey(date)) {
            dates.put(date, dates.get(date) + 1);
        } else {
            dates.put(date, 1);
        }
    }

    public List<Map.Entry<LocalDate, Integer>> getDates() {
        return List.copyOf(
            dates.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .toList());
    }

    public void addResource(String resource) {
        if (resources.containsKey(resource)) {
            resources.put(resource, resources.get(resource) + 1);
        } else {
            resources.put(resource, 1);
        }
    }

    public List<Map.Entry<String, Integer>> getResources() {
        return List.copyOf(
            resources.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList().reversed());
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void increaseRequestCount() {
        this.requestCount++;
    }

    public long getRequestSize() {
        return requestSize;
    }

    public void addRequestSize(long requestSize) {
        this.requestSize += requestSize;
    }
}
