package com.streamit.streaming_service.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "media_attributes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MediaAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String idioma;
    
    private String url;
}
