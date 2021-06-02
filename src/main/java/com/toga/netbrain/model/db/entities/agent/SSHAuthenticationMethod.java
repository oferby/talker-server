package com.toga.netbrain.model.db.entities.agent;

import org.springframework.data.neo4j.core.schema.Property;

public class SSHAuthenticationMethod extends AuthenticationMethod {

    private String managedElementAddress;

    private String username;

    private String password;

    public String getManagedElementAddress() {
        return managedElementAddress;
    }

    public void setManagedElementAddress(String managedElementAddress) {
        this.managedElementAddress = managedElementAddress;
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
}
