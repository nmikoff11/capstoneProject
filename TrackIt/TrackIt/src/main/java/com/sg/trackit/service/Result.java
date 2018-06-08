/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trackit.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 * @param <T>
 */
public class Result<T> {

    private boolean success = true;
    private List<String> messages = new ArrayList<>();
    private T payload;

    public boolean isSuccess() {
        return success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.success = false;
        this.messages.add(message);
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
