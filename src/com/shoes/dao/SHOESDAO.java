/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shoes.dao;

import java.util.List;

/**
 *
 * @author ntq04
 */
public abstract class SHOESDAO<EntityType, KeyType> {
    public abstract boolean insert(EntityType entity);
    public abstract boolean update(EntityType entity);
    public abstract List<EntityType> getAll();
}
