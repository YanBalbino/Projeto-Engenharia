import React, { useState } from 'react';
import '../register/checkbox.css'
import { Checkbox } from "antd";
import { ArrowLeftOutlined } from "@ant-design/icons";
const profiles = [
    {
        nome: "Rafael",
        infantil: false,
    },
    {
        nome: "Patrick",
        infantil: false,
    },
    {
        nome: "Samuca",
        infantil: false,
    }
]

const genders = [ 
    { label: 'Ação', value: 'acao' },
    { label: 'Aventura', value: 'aventura' },
    { label: 'Comédia', value: 'comedia' },
    { label: 'Drama', value: 'drama' },
    { label: 'Terror', value: 'terror' },
    { label: 'Suspense', value: 'suspense' },
    { label: 'Ficção Científica', value: 'ficcao_cientifica' },
    { label: 'Fantasia', value: 'fantasia' },
    { label: 'Romance', value: 'romance' },
    { label: 'Animação', value: 'animacao' },
    { label: 'Musical', value: 'musical' },
    { label: 'Documentário', value: 'documentario' },
    { label: 'Guerra', value: 'guerra' },
    { label: 'Policial', value: 'policial' },
    { label: 'Faroeste', value: 'faroeste' },
    { label: 'Esporte', value: 'esporte' },
    { label: 'Mistério', value: 'misterio' },
    { label: 'Biografia', value: 'biografia' },
    { label: 'Histórico', value: 'historico' },
    { label: 'Filme Noir', value: 'filme_noir' }
  ];

const Profile = () => {
    const [userProfile,setProfile] = useState(1)  //estado inicial
    const [modal,setModal] = useState(false)
    const [name,setName] = useState<string>('')
    const handleUserProfile = (n:number) =>{
        setProfile(n)
    }
    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setName(value);
      };

      const newProfile = () =>{
        profiles.push({nome:name,infantil:false})
        setModal(!modal)
      }
    return(
        <div className="flex flex-col flex-wrap items-center gap-20">
            <h1 className="text-[64px] mt-[50px]">Perfis de Usuário</h1>
            <div className="flex flex-row gap-24">
                {profiles.map((profile,index) => {
                    return(
                        <div className="flex flex-col items-center gap-5 hover:cursor-pointer  ">
                            <p className="text-[25px]">{profile.nome}</p> 
                            <img className={`${userProfile == index ? "w-56" : "w-40"}`} src = {`${process.env.PUBLIC_URL}/profile_icon.png`} alt="icone de perfil" onClick={() => handleUserProfile(index)} />
                        </div>
                        
                    )
                })}
                <div onClick={() => setModal(!modal)}className="flex flex-col items-center gap-5 hover:cursor-pointer">
                    <p className="text-[25px]">Novo Perfil</p> 
                    <img className="w-40" src = {`${process.env.PUBLIC_URL}/adicionar_perfil.png`} alt="icone de perfil" />
                </div>
            </div>
            {/* botão de adicionar perfil */}
            {modal && (
                 <div className="w-1/3 absolute left-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
                 <ArrowLeftOutlined onClick={() => setModal(!modal)} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
                 <h2 className="text-3xl">Criar perfil</h2>
                 <div className='flex flex-row gap-1'>
                   <div className='w-full flex flex-col gap-1'>
                     <label htmlFor="nomePerfil">Nome do perfil</label>
                     <input type="text" onChange={handleInput} name="nomePerfil" id="nomePerfil"  className="h-10 rounded-lg text-black p-2"   />
                   </div> 
                  
                 </div>
                 
                 <div className='w-full flex flex-col flex-wrap gap-1'>
                   <h3 className='self-center'>Gêneros preferidos</h3>
                   <div className='grid grid-cols-6 gap-1'>
                   {genders.map((gender) =>(
                     <Checkbox 
                     key={gender.value}
                     value={gender.value}
                     name = {gender.value}
                     
                     className='custom-checkbox text-white col-span-2 items-center'
                     >{gender.label}</Checkbox>
                   ))}
   
                   </div>
                 </div>
                 <button  type="button" onClick={newProfile} className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Criar Perfil</button>
             </div>
            )}
        </div>
    );
}
export default Profile;