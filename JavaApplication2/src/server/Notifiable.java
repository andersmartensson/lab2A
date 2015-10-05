/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Anders
 */
/**
 * * Notifiable.java * * The method notifyNewBid() is implemented by the client,
 * AuctionClient.java. * The method is used by the Auction server to "call back"
 * to update clients.
 */
import java.rmi.*;

public interface Notifiable extends Remote {

    public void notifyNewBid(int bid) throws RemoteException;
}
