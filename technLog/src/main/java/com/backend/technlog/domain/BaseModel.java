package com.backend.technlog.domain;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.Id;

@MappedSuperclass
@Data
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(generator = "idGenerator")
    protected String id;
}
