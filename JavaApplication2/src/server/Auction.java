/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 * * Auction.java * * This interface defines auction services, for clients to
 * call * through remote references. * * NB: Clients should call
 * registerForNotification * to register on the server.
 */
import java.rmi.*;

interface Auction extends Remote {

    public void setCurrentBid(int bid) throws RemoteException;

    public int getCurrentBid() throws RemoteException;

    public String getProduct() throws RemoteException;
    /* Called by clients to register for server callbacks */ public void registerForNotification(Notifiable n) throws RemoteException;

    public void deRegisterForNotification(Notifiable n) throws RemoteException;
}
