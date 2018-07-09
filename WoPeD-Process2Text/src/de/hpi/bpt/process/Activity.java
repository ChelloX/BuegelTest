package de.hpi.bpt.process;

abstract class Activity extends Node {
    Activity() {
        super();
    }

    Activity(String name, String desc) {
        super(name, desc);
    }

    Activity(String name) {
        super(name);
    }
}