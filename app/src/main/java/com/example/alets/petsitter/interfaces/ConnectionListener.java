package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.Connection;

import java.util.List;
/**
 * The class that make changes in Connection objects in the database have to implement this interface
 */
public interface ConnectionListener {
    void onConnectionLoaded(Connection con);
    void onConnectionLoaded(List<Connection> con);
    void onConectionCreated(Boolean created);
    void onConectionUpdated(Boolean created);
}
