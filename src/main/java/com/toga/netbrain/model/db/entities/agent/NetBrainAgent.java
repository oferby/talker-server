package com.toga.netbrain.model.db.entities.agent;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node({"NetBrainAgent", "Element"})
public class NetBrainAgent extends Element {

    @Property
    private AuthenticationMethod authenticationMethod;

    @Relationship("MANAGE")
    private Element managedElement;

    public AuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public Element getManagedElement() {
        return managedElement;
    }

    public void setManagedElement(Element managedElement) {
        this.managedElement = managedElement;
    }
}
