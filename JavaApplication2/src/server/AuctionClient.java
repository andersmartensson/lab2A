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
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class AuctionClient extends UnicastRemoteObject implements Notifiable {

    private Auction auction; // Reference to remote server object private String product;
    private String product;
    
    public AuctionClient(Auction auction) throws RemoteException {
        super();
        this.auction = auction;
        this.product = auction.getProduct();
    }
    /* This method is used by the Auction server to "call back" to update clients. */ public void notifyNewBid(int bid) throws RemoteException {
        System.out.println("Highest bid is now " + bid);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java AuctionClient <server_host> <product>");
            System.exit(0);
        }
        try {
            String url = "rmi://" + args[0] + "/" + args[1];
            Auction auction = (Auction) Naming.lookup(url);
            AuctionClient client = new AuctionClient(auction); /* Register for callbacks at the auction server. */

            auction.registerForNotification(client);
            client.runClient();
        } catch (NotBoundException nbe) {
            System.out.println(nbe.toString());
            System.out.println(args[1] + " is not available");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runClient() throws RemoteException {
        Scanner scan = new Scanner(System.in);
        char ans;
        do {
            System.out.println("1. Put a bid 2. Get current bid 0. Exit");
            ans = scan.nextLine().charAt(0);
            switch (ans) {
                case '1':
                    System.out.println("New bid? ");
                    String line = scan.nextLine();
                    int bid = Integer.parseInt(line.trim());
                    auction.setCurrentBid(bid);
                    break;
                case 
                    '2':
                    int current = auction.getCurrentBid();
                    System.out.println("Highest bid is now " + current);
                    break;
                case 
                    '0':
                    break;
                default:
                    System.out.println("Huh?");
            }
        } while (ans != '0');
System.out.println("Exiting...");
// Important: Deregister for notifiation from server!
        auction.deRegisterForNotification(this);
// Terminate the thread associated with rmi calls System.exit(0);
    }
}
