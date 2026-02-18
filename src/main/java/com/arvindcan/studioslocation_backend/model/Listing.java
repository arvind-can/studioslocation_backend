package com.arvindcan.studioslocation_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="listings")
public class Listing {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String title;
  private String description;
  private int price;
  private int surface;
  private String city;
  private int postalCode;
  @Column(name = "has_furniture")
  private boolean hasFurniture;
  @Column(name = "has_attached_toilet")
  private boolean hasAttachedToilet;
  @Column(name = "has_attached_kitchen")
  private boolean hasAttachedKitchen;
  @Column(name = "created_at")
  private Date createdAt;
  @Column(name = "updated_at")
  private Date updatedAt;
}

