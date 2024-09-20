package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SeriesModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "media_id")
	private MediaModel media;
    
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<SeasonModel> seasons;
    
}