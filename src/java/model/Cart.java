/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }
    
    public void setItems(List<Item> items){
        this.items = items;
    }
    
    public List<Item> getItems(){
        return items;
    }

    public Cart(List<Item> items) {
        this.items = items;
    }
    
    //get Product by id
    public Item getItemById(int id){
        for (Item i : items) {
            if(i.getProduct().getProductID()==id){
                return i;
            }
            else{
                return null;
            }
        }
        return null;
    }
    
    //get quantity of Item
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    
    //add Item to Cart
    public void addItem(Item t){
        //Case 1: Product exist in cart
        if(getItemById(t.getProduct().getProductID()) != null){
            Item i = getItemById(t.getProduct().getProductID());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        }
        //Case 2: Product not exist in cart
        else{
            items.add(t);
        }
    }
    
    //remove Item in card
    public void removeItem(int id){
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
    
    //calculator Total price Items
    public double totalPrice(){
        double total = 0;
        for (Item i : items) {
            total += i.getPrice() * i.getQuantity();
        }
        return total;
    }
}
