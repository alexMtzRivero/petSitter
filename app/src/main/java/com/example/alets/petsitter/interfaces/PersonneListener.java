package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Personne;

import java.util.List;
/**
 * The class that make changes in Personne objects in the database have to implement this interface.
 */
public interface PersonneListener {
    void onPersonneLoaded(Personne p);
    void onPersonneLoaded(List<Personne> p);
    void onPersonneCreated(Boolean created);
    void onPersonneUpdate(Boolean created);
}
