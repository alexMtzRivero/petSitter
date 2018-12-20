package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Animal;

import java.util.List;

/**
 * The class that make changes in animal objects in the database have to implement this interface
 */
public interface AnimalListner {
    void onLoadAnimal(Animal a);
    void onLoadAnimal(List<Animal> a);
    void onAnimalCreated(Boolean created);
    void onAnimalUpdated(Boolean created);
}
