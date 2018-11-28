package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Connection;

public interface ConnectionListener {
    public  void onConnectionLoaded(Connection con);
    public  void onConnectionLoaded(Connection [] con);
    void onConectionCreated(Boolean created);
    void onConectionUpdated(Boolean created);
}
