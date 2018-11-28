package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Personne;

public interface PersonneListener {
    public void onPersonneLoaded(Personne p);
    public void onPersonneLoades(Personne [] p);
    void onPersonneCreated(Boolean created);
    void onPersonneUpdate(Boolean created);
}
