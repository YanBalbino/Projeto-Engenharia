import React, { useState } from 'react';

const SeriesRegister = () =>{

    const [formData, setFormData] = useState({
        titulo: '',
      });

      const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData(prevData => ({
          ...prevData,
          [name]: value
        }));
      };

      const handleSubmit = async () => {
    
        const data = {
            "media":{
                "faixaEtaria": "DEZOITO",
                "actorIds": [
                ],
                "atores": [
                    {
                    "imagemUrl": "https://exemplo.com/leonardo.jpg",
                    "nome": "Leonardo DiCaprio"
                    },
                    {
                    "imagemUrl": "https://exemplo.com/joseph.jpg",
                    "nome": "Joseph Gordon-Levitt"
                    }
                ]
            },
            "seasons": [
                {
                    "seasonNumber" : 1,
                    "episodes" : [
                        {
                            "numeroEpisodio" : 1, 
                            "tituloEpisodio" : "ep1",
                            "duracaoEpisodio" : 46,
                            "videoUrl": "https://exemplo.com/ep1",
                            "legendasDisponiveis": [
                                {
                                    "legendaUrl": "/subs/playlists_rickandmorty/Rica",
                                    "idioma": "Portuguese"
                                },
                                {
                                    "legendaUrl": "https://example.com/subtitles/es",
                                    "idioma": "Spanish"
                                }
                            ],
                            "audiosDisponiveis": [
                                {
                                    "audioUrl": "https://example.com/audio/en",
                                    "idioma": "English"
                                },
                                {
                                    "audioUrl": "https://example.com/audio/fr",
                                    "idioma": "French"
                                }
                            ],

                        },
                        {
                            "numeroEpisodio" : 2, 
                            "tituloEpisodio" : "ep2",
                            "duracaoEpisodio" : 48,
                            "videoUrl": "https://exemplo.com/ep2",
                            "legendasDisponiveis": [
                                {
                                    "legendaUrl": "/subs/playlists_rickandmorty/Rica",
                                    "idioma": "Portuguese"
                                },
                                {
                                    "legendaUrl": "https://example.com/subtitles/es",
                                    "idioma": "Spanish"
                                }
                            ],
                            "audiosDisponiveis": [
                                {
                                    "audioUrl": "https://example.com/audio/en",
                                    "idioma": "English"
                                },
                                {
                                    "audioUrl": "https://example.com/audio/fr",
                                    "idioma": "French"
                                }
                            ],

                        },
                        {
                            "numeroEpisodio" : 3, 
                            "tituloEpisodio" : "ep3",
                            "duracaoEpisodio" : 42,
                            "videoUrl": "https://exemplo.com/ep3",
                            "legendasDisponiveis": [
                                {
                                    "legendaUrl": "/subs/playlists_rickandmorty/Rica",
                                    "idioma": "Portuguese"
                                },
                                {
                                    "legendaUrl": "https://example.com/subtitles/es",
                                    "idioma": "Spanish"
                                }
                            ],
                            "audiosDisponiveis": [
                                {
                                    "audioUrl": "https://example.com/audio/en",
                                    "idioma": "English"
                                },
                                {
                                    "audioUrl": "https://example.com/audio/fr",
                                    "idioma": "French"
                                }
                            ],

                        },
                        {
                            "numeroEpisodio" : 4, 
                            "tituloEpisodio" : "ep4",
                            "duracaoEpisodio" : 55,
                            "videoUrl": "https://exemplo.com/ep4",
                            "legendasDisponiveis": [
                                {
                                    "legendaUrl": "/subs/playlists_rickandmorty/Rica",
                                    "idioma": "Portuguese"
                                },
                                {
                                    "legendaUrl": "https://example.com/subtitles/es",
                                    "idioma": "Spanish"
                                }
                            ],
                            "audiosDisponiveis": [
                                {
                                    "audioUrl": "https://example.com/audio/en",
                                    "idioma": "English"
                                },
                                {
                                    "audioUrl": "https://example.com/audio/fr",
                                    "idioma": "French"
                                }
                            ],

                        },
                        
                    ]
                }
                
            ]
        };
    
        try {
          const formattedTitle = encodeURIComponent(formData.titulo);
          const token = localStorage.getItem('token');
          const response = await fetch(`http://localhost:8080/api/series/${formattedTitle}`, {
            method: 'POST',
            headers: {
              'Authorization': `Bearer ${token}`, 
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
    
        if (!response.ok) {
          const error = await response.json()
          console.log(error)
        } else {
          console.log(await response.json())
        }
        } catch (error) {
          console.error(error);
        }
        
      };
    return(
        <div className="flex flex-col">
            <label htmlFor="">título</label>
            <input type="text" name= "titulo" className="text-white bg-black" onChange={handleInput}/>
            
            <button onClick={handleSubmit}>cadastrar</button>
        </div>
    )
}

export default SeriesRegister;