package com.hzgroup.javers;

import com.hzgroup.javers.model.Position;
import org.javers.core.metamodel.annotation.Id;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
public class Person {
    @Id
    private String name;
    private String favor;

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Person() {
    }

    public Person(String name, String favor) {
        this.name = name;
        this.favor = favor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }
}
