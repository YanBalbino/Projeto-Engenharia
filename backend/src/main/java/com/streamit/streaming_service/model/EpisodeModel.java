package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "episodes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EpisodeModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private Integer numeroEpisodio;
    
    private String tituloEpisodio;
    
    private Integer duracaoEpisodio; // in minutes
    
    @Column(unique = true)
	private String videoUrl;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "season_id")
    private SeasonModel temporada;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "episode_subtitles",
            joinColumns = @JoinColumn(name = "episode_id"),
            inverseJoinColumns = @JoinColumn(name = "subtitle_id")
        )
    private List<SubtitleModel> legendasDisponiveis;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "episode_audios",
            joinColumns = @JoinColumn(name = "episode_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
        )
    private List<AudioModel> audiosDisponiveis;

}
