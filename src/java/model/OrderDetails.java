/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAOProducts;

public class OrderDetails {
    private Orders Order;
    private int ProductID;
    private int Quantity;

    public OrderDetails() {
    }

    public OrderDetails(Orders Order, int ProductID, int Quantity) {
        this.Order = Order;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }

    public Orders getOrder() {
        return Order;
    }

    public void setOrder(Orders Order) {
        this.Order = Order;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getProducNameById(){
        DAOProducts dao = new DAOProducts();
        return dao.getProductByID(this.getProductID()).getProductName();
    }
    
}
