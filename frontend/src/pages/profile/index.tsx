import React, { useState, useEffect } from 'react';
import '../register/checkbox.css';
import { Checkbox } from "antd";
import { ArrowLeftOutlined } from "@ant-design/icons";
import type { CheckboxChangeEvent } from 'antd/es/checkbox';

interface Profile {
  id: string,
  nome: string,
  iconUrl: string,
  perfilInfantil: boolean,
  generosPreferidos: string[],
}

const genres = [
  { label: 'Ação', value: 'action' },
  { label: 'Aventura', value: 'adventure' },
  { label: 'Comédia', value: 'comedy' },
  { label: 'Drama', value: 'drama' },
  { label: 'Terror', value: 'horror' },
  { label: 'Suspense', value: 'thriller' },
  { label: 'Ficção Científica', value: 'science_fiction' },
  { label: 'Fantasia', value: 'fantasy' },
  { label: 'Romance', value: 'romance' },
  { label: 'Animação', value: 'animation' },
  { label: 'Musical', value: 'musical' },
  { label: 'Documentário', value: 'documentary' },
  { label: 'Guerra', value: 'war' },
  { label: 'Policial', value: 'crime' },
  { label: 'Faroeste', value: 'western' },
  { label: 'Esporte', value: 'sports' },
  { label: 'Mistério', value: 'mystery' },
  { label: 'Biografia', value: 'biography' },
  { label: 'Histórico', value: 'historical' },
];

const Profile = () => {
  const [userProfile, setProfile] = useState(1);
  const [userProfiles, setUserProfiles] = useState<Profile[]>([]);
  const [modal, setModal] = useState(false);
  const [name, setName] = useState<string>('');
  const [selectedGenres, setSelectedGenres] = useState<string[]>([]);
  const [isInfantilProfile, setIsInfantilProfile] = useState<boolean>(false);

  const handleUserProfile = (n: number, id: string) => {
    localStorage.setItem('currentProfile', id);
    setProfile(n);
  };

  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setName(value);
  };

  const handleCheckbox = (e: CheckboxChangeEvent) => {
    const { value, checked } = e.target;
    setSelectedGenres((prevValues) => {
      if (checked) {
        return [...prevValues, value as string];
      }
      return prevValues.filter((item) => item !== value);
    });
  };

  const handleInfantilCheckbox = (e: CheckboxChangeEvent) => {
    setIsInfantilProfile(e.target.checked); 
  };

  const getUserProfiles = async () => {
    const id = localStorage.getItem('idUser');
    const token = localStorage.getItem('token');
    try {
      const response = await fetch(`http://localhost:8080/api/profiles/user/${id}`, {
        method: 'GET',  
        headers: {
          'Authorization': `Bearer ${token}`,  
          'Content-Type': 'application/json'   
        }
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.log(errorData);
      } else {
        const profiles: Profile[] = await response.json();
        setUserProfiles(profiles);
      }
    } catch (error) {
      console.error('Erro ao pegar perfis:', error);
    }
  };

  const newProfile = async () => {
    const id = localStorage.getItem('idUser');
    const token = localStorage.getItem('token');
    const data = {
      nome: name,
      iconUrl: "",
      perfilInfantil: isInfantilProfile,
      generosPreferidos: selectedGenres,
    };
    try {
      const response = await fetch(`http://localhost:8080/api/profiles/user/${id}`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`, 
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.log(errorData);
      } else {
        getUserProfiles();
      }
    } catch (error) {
      console.error('Erro ao criar o usuário:', error);
    }
    setModal(!modal);
  };

  useEffect(() => {
    getUserProfiles();
  }, []);

  return (
    <div className="flex flex-col flex-wrap items-center gap-20 pt-10">
      <h1 className="text-5xl">Perfis de Usuário</h1>
      <div className="flex flex-row gap-24">
        {userProfiles.map((profile, index) => (
          <div className="flex flex-col items-center gap-5 hover:cursor-pointer" key={profile.id}>
            <p className="text-[25px]">{profile.nome}</p>
            <img
              className={`${userProfile === index ? "w-56" : "w-40"}`}
              src={`${process.env.PUBLIC_URL}/profile_icon.png`}
              alt="icone de perfil"
              onClick={() => handleUserProfile(index, profile.id)}
            />
          </div>
        ))}
        <div onClick={() => setModal(!modal)} className="flex flex-col items-center gap-5 hover:cursor-pointer">
          <p className="text-[25px]">Novo Perfil</p>
          <img className="w-24" src={`${process.env.PUBLIC_URL}/adicionar_perfil.png`} alt="icone de perfil" />
        </div>
      </div>

      {modal && (
        <div className="w-1/3 absolute top left-1/3 flex flex-col border border-gray-500 rounded-xl bg-slate-800 p-10 gap-5 items-center">
          <ArrowLeftOutlined onClick={() => setModal(!modal)} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150" />
          <h2 className="text-3xl">Criar perfil</h2>

          <div className="flex flex-row gap-1 w-full">
            <div className="w-full flex flex-col gap-1">
              <label htmlFor="nomePerfil">Nome do perfil</label>
              <input type="text" onChange={handleInput} name="nomePerfil" id="nomePerfil" className="h-8 rounded-lg text-black p-2" />
            </div>
          </div>

          <div className="w-full flex flex-col flex-wrap gap-1">
            <h3 className="self-center">Gêneros preferidos</h3>
            <div className="grid grid-cols-3 gap-2">
              {genres.map((genre) => (
                <Checkbox
                  key={genre.value}
                  value={genre.value}
                  name={genre.value}
                  onChange={handleCheckbox}
                  className="custom-checkbox text-white"
                >
                  {genre.label}
                </Checkbox>
              ))}
            </div>
          </div>

          {/* Checkbox para perfil infantil */}
          <div className="w-full flex items-center gap-2 mt-2">
            <Checkbox onChange={handleInfantilCheckbox} checked={isInfantilProfile} className="custom-checkbox text-white">
              Perfil Infantil?
            </Checkbox>
          </div>

          <button type="button" onClick={newProfile} className="w-11/12 h-10 rounded-lg bg-[#216EAD] hover:bg-sky-600">Criar Perfil</button>
        </div>
      )}
    </div>
  );
};

export default Profile;
