package com.machinecoding.models;

import netscape.javascript.JSObject;

public class Message {
    private JSObject object;

    public Message(JSObject object) {
        this.object = object;
    }

    public JSObject getObject() {
        return object;
    }
}
