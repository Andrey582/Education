package edu.hw9.Task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {

    private final Map<String, Double> metricMax;
    private final Map<String, Double> metricMin;
    private final Map<String, Double> metricSum;
    private final Map<String, Double> metricAvg;
    private final Map<String, Integer> metricCount;
    private ExecutorService servise = Executors.newCachedThreadPool();

    public StatsCollector() {
        metricMax = new HashMap<>();
        metricMin = new HashMap<>();
        metricAvg = new HashMap<>();
        metricSum = new HashMap<>();
        metricCount = new HashMap<>();
    }

    public void push(String metricName, double[] metricsValue) {
        Future<Double> sum;
        Future<Double> avg;
        Future<Double> min;
        Future<Double> max;
        synchronized (this) {
            if (metricMax.containsKey(metricName)) {
                max = servise.submit(() -> metricMax.put(metricName, max(metricMax.get(metricName), metricsValue)));
                min = servise.submit(() -> metricMin.put(metricName, min(metricMin.get(metricName), metricsValue)));
                avg = servise.submit(() ->
                    metricAvg.put(metricName, avg(metricName, metricAvg.get(metricName), metricsValue)));
                sum = servise.submit(() -> metricSum.put(metricName, sum(metricSum.get(metricName), metricsValue)));
            } else {
                max = servise.submit(() -> metricMax.put(metricName, max(Double.MIN_VALUE, metricsValue)));
                min = servise.submit(() -> metricMin.put(metricName, min(Double.MAX_VALUE, metricsValue)));
                avg = servise.submit(() -> metricAvg.put(metricName, avg(metricName, 0, metricsValue)));
                sum = servise.submit(() -> metricSum.put(metricName, sum(0, metricsValue)));
            }
            try {
                max.get();
                min.get();
                sum.get();
                avg.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Map.Entry<String, Map<String, Double>>> stats() {
        synchronized (this) {
            Map<String, Map<String, Double>> result = new HashMap<>();
            for (String metricName : metricSum.keySet()) {
                Future<Double> sum = servise.submit(() -> metricSum.get(metricName));
                Future<Double> avg = servise.submit(() -> metricAvg.get(metricName));
                Future<Double> max = servise.submit(() -> metricMax.get(metricName));
                Future<Double> min = servise.submit(() -> metricMin.get(metricName));
                try {
                    result.put(metricName,
                        Map.of(
                            "Sum", sum.get(),
                            "Avg", avg.get(),
                            "Max", max.get(),
                            "Min", min.get()
                        ));
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            return result.entrySet();
        }
    }

    public void close() {
        servise.close();
    }

    private double sum(double lastValue, double[] newValues) {
        double sum = lastValue;
        for (double value : newValues) {
            sum += value;
        }
        return sum;
    }

    private double avg(String metricName, double lastValue, double[] newValue) {
        int count = 0;
        if (metricCount.containsKey(metricName)) {
            count = metricCount.get(metricName);
        }
        double avg = lastValue * count;
        count += newValue.length;
        for (double value : newValue) {
            avg += value;
        }
        avg /= count;
        metricCount.put(metricName, count);
        return avg;
    }

    private double max(double lastValue, double[] newValues) {
        double max = lastValue;
        for (double value : newValues) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    private double min(double lastValue, double[] newValues) {
        double min = lastValue;
        for (double value : newValues) {
            if (min > value) {
                min = value;
            }
        }
        return min;
    }
}
