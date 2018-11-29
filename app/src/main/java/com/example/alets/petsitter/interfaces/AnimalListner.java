package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Animal;

import java.util.List;

public interface AnimalListner {
    public void onLoadAnimal(Animal a);
    public void onLoadAnimal(List<Animal> a);
    void onAnimalCreated(Boolean created);
    void onAnimalUpdated(Boolean created);
}
