package com.streamit.streaming_service.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "subtitles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SubtitleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attributes_id")
    private MediaAttributes attributes;
    
}
