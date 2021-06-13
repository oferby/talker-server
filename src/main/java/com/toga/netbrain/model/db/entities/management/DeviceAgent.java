package com.toga.netbrain.model.db.entities.management;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class DeviceAgent extends Element {

    private String target;

    private String username;

    private String password;

    public DeviceAgent() {
    }

    public DeviceAgent(String target) {
        this.target = target;
    }

    public DeviceAgent(String target, String username, String password) {
        this.target = target;
        this.username = username;
        this.password = password;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DeviceAgent{" +
                "name=" + name + '\'' +
                "target='" + target + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
