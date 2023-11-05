package edu.Validation;

import edu.Animal;

public class AgeError implements ValidationError {

    private String errorInfo;

    public AgeError(Animal animal) {
        checkAnimal(animal);
    }

    public AgeError(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    @SuppressWarnings("MagicNumber")
    public void checkAnimal(Animal animal) {
        switch (animal.type()) {
            case CAT -> {
                if (animal.age() > 20) {
                    errorInfo = "- Incorrect cat age";
                }
            }
            case DOG -> {
                if (animal.age() > 15) {
                    errorInfo = "- Incorrect dog age";
                }
            }
            case BIRD -> {
                if (animal.age() > 50) {
                    errorInfo = "- Incorrect bird age";
                }
            }
            case FISH -> {
                if (animal.age() > 35) {
                    errorInfo = "- Incorrect fish age";
                }
            }
            case SPIDER -> {
                if (animal.age() > 20) {
                    errorInfo = "- Incorrect spider age";
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
        if (obj instanceof AgeError ageError) {
            return errorInfo.equals(ageError.errorInfo);
        }
        return false;
    }
}
