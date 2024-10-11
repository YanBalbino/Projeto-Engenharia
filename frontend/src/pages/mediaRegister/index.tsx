import React, { useState } from 'react';

const MediaRegister = () =>{

    const [formData, setFormData] = useState({
        titulo: '',
        ano: '',
        genero:'',
        descricao: '',
        diretor: '',
        img: '',
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
            titulo: formData.titulo,
            ano: formData.ano,
            genero: formData.genero,
            descricao: formData.descricao,
            diretor: formData.diretor,
            img: formData.img,
        };
    
        try {
        const response = await fetch('http://localhost:8080/api/medias', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
    
        if (!response.ok) {
          
        } else {
    
        }
        } catch (error) {
        console.error('Erro ao criar o usuário:', error);
        }
        
      };
    return(
        <div className="flex flex-col">
            <label htmlFor="">título</label>
            <input type="text" name= "titulo" className="text-white bg-black" onChange={handleInput}/>
            <label htmlFor="">ano de producao</label>
            <input type="text" name= "anoProducao" className="text-white bg-black" onChange={handleInput}/>
            <label htmlFor="">gênero</label>
            <input type="text" name= "genero" className="text-white bg-black" onChange={handleInput}/>
            <label htmlFor="">descrição</label>
            <input type="text" name= "descricao" className="text-white bg-black" onChange={handleInput}/>
            <label htmlFor="">diretor</label>
            <input type="text" name= "diretor" className="text-white bg-black" onChange={handleInput}/>
            <label htmlFor="">img</label>
            <input type="text" name= "imgurl"className="text-white bg-black" onChange={handleInput}/>

            <button onClick={handleSubmit}>cadastrar</button>
        </div>
    )
}

export default MediaRegister;