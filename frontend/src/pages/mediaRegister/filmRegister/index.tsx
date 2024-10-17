import React, { useState } from 'react';

const FilmRegister = () =>{

    const [formData, setFormData] = useState({
        titulo: '',
        videoUrl: '',
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
                "duracao": 148,
                "videoUrl": formData.videoUrl,
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
                "atores": [
                  {
                    "nome": "Tom Hardy",
                    "imagemUrl": "https://example.com/images/tom_hardy.jpg"
                  },
                  {
                    "nome": "Ellen Page",
                    "imagemUrl": "https://example.com/images/ellen_page.jpg"
                  }
                ],
                "actorIds": []
        };
    
        try {
          const formattedTitle = encodeURIComponent(formData.titulo);
          const token = localStorage.getItem('token');
          const response = await fetch(`http://localhost:8080/api/films/${formattedTitle}`, {
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
            <label htmlFor="">videourl</label>
            <input type="text" name= "videoUrl" className="text-white bg-black" onChange={handleInput}/>
            <button onClick={handleSubmit}>cadastrar</button>
        </div>
    )
}

export default FilmRegister;