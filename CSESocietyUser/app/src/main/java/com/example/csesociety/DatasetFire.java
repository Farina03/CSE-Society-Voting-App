package com.example.csesociety;

public class DatasetFire {

    String poll_name;
    String poll_description;

    public DatasetFire(String poll_name, String poll_description) {
        this.poll_name = poll_name;
        this.poll_description = poll_description;
    }

    public DatasetFire() {
    }

    public String getPoll_name() {
        return poll_name;
    }

    public String getPoll_description() {
        return poll_description;
    }

    public void setPoll_name(String poll_name) {
        this.poll_name = poll_name;
    }

    public void setPoll_description(String poll_description) {
        this.poll_description = poll_description;
    }
}
