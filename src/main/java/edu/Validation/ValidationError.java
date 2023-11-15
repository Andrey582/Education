package edu.Validation;

import edu.Animal;

public interface ValidationError {

    void checkAnimal(Animal animal);

    String getErrorInfo();
}
