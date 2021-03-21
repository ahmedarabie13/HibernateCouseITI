package com.arabie.dao;// default package
// Generated Mar 20, 2021, 1:49:38 PM by Hibernate Tools 6.0.0.Alpha2


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BuyerBidProduct generated by hbm2java
 */

public class BuyerBidProduct  implements java.io.Serializable {


     private BuyerBidProductId id;
     private Buyer buyer;
     private Product product;
     private Date date;
     private float amount;
     private int quantity;

    public BuyerBidProduct() {
    }

    public BuyerBidProduct(BuyerBidProductId id, Buyer buyer, Product product, Date date, float amount, int quantity) {
       this.id = id;
       this.buyer = buyer;
       this.product = product;
       this.date = date;
       this.amount = amount;
       this.quantity = quantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="buyerId", column=@Column(name="buyer_id", nullable=false) ), 
        @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false) ) } )
    public BuyerBidProductId getId() {
        return this.id;
    }
    
    public void setId(BuyerBidProductId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="buyer_id", nullable=false, insertable=false, updatable=false)
    public Buyer getBuyer() {
        return this.buyer;
    }
    
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable=false, length=10)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    
    @Column(name="amount", nullable=false, precision=12, scale=0)
    public float getAmount() {
        return this.amount;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}


