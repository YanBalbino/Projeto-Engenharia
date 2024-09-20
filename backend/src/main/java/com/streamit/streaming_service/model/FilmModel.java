package com.streamit.streaming_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "films")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FilmModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "media_id")
	private MediaModel media;

	private Integer duracao; // em minutos
	
	@Column(unique = true)
	private String videoUrl;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_subtitles",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "subtitle_id")
        )
    private List<SubtitleModel> legendasDisponiveis;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_audios",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id")
        )
    private List<AudioModel> audiosDisponiveis;

}