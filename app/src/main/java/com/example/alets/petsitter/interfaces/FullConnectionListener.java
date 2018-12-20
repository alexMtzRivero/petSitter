package com.example.alets.petsitter.interfaces;

import com.example.alets.petsitter.pojos.FullInformation;

/**
 * The class that uses objectd type FullController have to implement this interface to properly handel it when it charges.
 */

public interface FullConnectionListener {
    void  informationLoaded( FullInformation fullInformation);
}
