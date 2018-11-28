package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Animal;

public interface AnimalListner {
    public void onLoadAnimal(Animal a);
    public void onLoadAnimal(Animal []a);
    void onAnimalCreated(Boolean created);
    void onAnimalUpdated(Boolean created);
}
