/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pupil
 */
@Entity
public class Product implements Serializable{

//=============================== VARIABLES
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(fetch=FetchType.EAGER)
    private int id;
    @NotNull @Basic(fetch=FetchType.EAGER)
    private String name;
    @NotNull @Basic(fetch=FetchType.EAGER)
    private double price;
    @NotNull @Basic(fetch=FetchType.EAGER)
    private int quantity;
    @NotNull @Basic(fetch=FetchType.EAGER)
    private boolean deleted;

//=============================== CONSTRUCTORS 
    public Product(){
        this.deleted = false;
    }
    public Product(String name, double price, int quantity){
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.deleted = false;
    }

    
//=============================== METHODS
    private String getDeletedString(){
        String str = "";
        if(deleted){
            str = "\u001B[41m" + "\u001B[37m" + " DELETED " + "\u001B[0m" + " ";
        }
        return str;
    }
//=============================== GETTERS
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getId() {
        return id;
    }
    public boolean isDeleted(){
        return deleted;
    }
    
//=============================== SETTERS
    public void setName(String name){
        this.name = name;       
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
  
    
//=============================== OVERRIDDEN METHODS    
//to string
    @Override
    public String toString() {
        return getDeletedString()+"Product " + id + " {" + "model=" + name + ", cost=" + price + ", quantity=" + quantity + '}';
    }
    public String getData() {
        return "Product " + id + " {" + "model=" + name + ", cost=" + price + "}";
    }
    
    
//hash code    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 89 * hash + this.quantity;
        return hash;
    }

//equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }
}
