//import java.awt.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.ArrayList;
//
///**
// * Created by darionmoore on 12/1/16.
// */
//public class Inventory {
//    private ArrayList<Item> items = new ArrayList<>();
//    private Scanner input = new Scanner(System.in);
//    private Item newItem = new Item();
//    private int itemId = 100;
//
//
//
//    public Inventory() {
//        items.add(newItem);
//    }
//    public void itemList() {
//        for(Item item : items) {
//            System.out.format("ID%s\n", itemId++);
//            System.out.format("Name: %s\n", newItem.getName());
//            System.out.format("Price: %s\n", newItem.getPrice());
//            System.out.format("Stock: %s\n", newItem.getStock());
//        }
//    }
//    public void getId() {
//        this.itemId = items.indexOf(itemId);
//    }
//
//    public void options() throws Exception {
//        boolean run = true;
//        while(run) {
//            System.out.println("What would you like to do?\n");
//            System.out.println("[1] Make an Item? Add the price and stock. \n");
//            System.out.println("[2] Sell an item? \n");//check if there are enough left in inventory and if not throw an exception
//            System.out.println("[3] Remove an item? \n");
//            System.out.println("[4] Update an items quantity? \n");
//            System.out.println("[5] Update an items price? \n");
//            System.out.println("[6] Check Inventory.\n");
//            System.out.println("[7] Quit? \n");
//            int choices = input.nextInt();
//            if (choices == 1) {
//                System.out.println("Name? \n");
//                newItem.setName();
//                System.out.println("Price? \n");
//                newItem.setPrice();
//                System.out.println("Stock? \n");
//                newItem.setStock();
//                items.add(newItem);
//            } else if (choices == 2) {
//                System.out.println("Choose an ID. \n");
//                this.getId();
//                input.nextInt();
//                System.out.println("How many? \n");
//                newItem.setStock();
//                newItem.sellItem();
//                items.remove(newItem);
//            } else if (choices == 3) {
//                System.out.println("What would you like to remove?\n");
//                this.getId();
//                input.nextInt();
//                items.remove(newItem);
//            } else if (choices == 4) {
//                System.out.println("What item would you like to update?\n");
//                this.getId();
//                input.nextInt();
//                System.out.println("How amny would you like in the inventory?\n");
//                newItem.setBackStock();
//                items.add(newItem);
//            } else if (choices == 5) {
//                System.out.println("What items price would you like to change?");
//                this.getId();
//                input.nextInt();
//                newItem.setPrice();
//                items.add(newItem);
//            }else if(choices == 6){
//                System.out.println("Here's what is in stock: \n");
//                for(Item item : items) {
//                    System.out.format(" Name: %s %s\n", itemId++, newItem.getName());
//                    System.out.format("Price: %s\n", newItem.getPrice());
//                    System.out.format("Stock: %s\n", newItem.getStock());
//                }
//            } else if (choices == 7) {
//                run = false;
//            }
//        }
//
//
//
//
//    }
//}
//
//
//













