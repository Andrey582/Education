package edu.Validation;

import edu.Animal;

public class WeightError implements ValidationError {

    private String errorInfo;

    public WeightError(Animal animal) {
        checkAnimal(animal);
    }

    public WeightError(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    @SuppressWarnings("MagicNumber")
    public void checkAnimal(Animal animal) {
         switch (animal.type()) {
            case CAT -> {
                if (animal.weight() < 2000 || 6000 < animal.weight()) {
                    errorInfo = "- Incorrect cat weight";
                }
            }
            case DOG -> {
                if (animal.weight() < 1000 || 10000 < animal.weight()) {
                    errorInfo = "- Incorrect dog weight";
                }
            }
            case BIRD -> {
                if (animal.weight() < 50 || 2000 < animal.weight()) {
                    errorInfo = "- Incorrect bird weight";
                }
            }
            case FISH -> {
                if (animal.weight() < 300 || 7000 < animal.weight()) {
                    errorInfo = "- Incorrect fish weight";
                }
            }
            case SPIDER -> {
                if (animal.weight() < 1 || 150 < animal.weight()) {
                    errorInfo = "- Incorrect spider weight";
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
        if (obj instanceof WeightError weightError) {
            return errorInfo.equals(weightError.errorInfo);
        }
        return false;
    }
}
