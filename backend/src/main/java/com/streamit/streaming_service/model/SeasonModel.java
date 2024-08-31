package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "seasons")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SeasonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private int seasonNumber;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "series_id")
    private SeriesModel serie;
    
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    private List<EpisodeModel> episodes;
}
