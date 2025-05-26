/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ProductCart {
    public int quantity;
    public Products product;
    
    public ProductCart(int quantity, Products product){
        this.quantity = quantity;
        this.product = product;
    }
    
    public void increamentQuantity(){
        this.quantity++;
    }
    
}
