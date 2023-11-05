package edu.Validation;

import edu.Animal;

public class HeightError implements ValidationError {

    private String errorInfo;

    public HeightError(Animal animal) {
        checkAnimal(animal);
    }

    public HeightError(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    @SuppressWarnings("MagicNumber")
    public void checkAnimal(Animal animal) {
        switch (animal.type()) {
            case CAT -> {
                if (animal.height() < 20 || 30 < animal.height()) {
                    errorInfo = "- Incorrect cat height";
                }
            }
            case DOG -> {
                if (animal.height() < 10 || 60 < animal.height()) {
                    errorInfo = "- Incorrect dog height";
                }
            }
            case BIRD -> {
                if (animal.height() < 15 || 60 < animal.height()) {
                    errorInfo = "- Incorrect bird height";
                }
            }
            case FISH -> {
                if (animal.height() < 10 || 50 < animal.height()) {
                    errorInfo = "- Incorrect fish height";
                }
            }
            case SPIDER -> {
                if (animal.height() < 1 || 10 < animal.height()) {
                    errorInfo = "- Incorrect spider height";
                }
            }
            default -> {
                // nothing
            }
        }
    }

    @Override
    public int hashCode() {
        return 31 + errorInfo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HeightError heightError) {
            return errorInfo.equals(heightError.errorInfo);
        }
        return false;
    }
}
