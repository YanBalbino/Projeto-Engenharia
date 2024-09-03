import React, { useState } from 'react';

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

const Profile = () => {
    const [userProfile,setProfile] = useState(1)  //estado inicial

    const handleUserProfile = (n:number) =>{
        setProfile(n)
    }
    return(
        <div className="w-screen h-screen bg-gradient-to-b from-black to-cyan-950 text-white flex flex-col flex-wrap items-center gap-20">
            <h1 className="text-[64px] mt-[100px]">Perfis de Usuário</h1>
            <div className="flex flex-row gap-24">
                {profiles.map((profile,index) => {
                    return(
                        <div className="flex flex-col items-center gap-5 ">
                            <p className="text-[25px]">{profile.nome}</p> 
                            <img className={`${userProfile == index ? "w-56" : "w-40"}`} src = {`${process.env.PUBLIC_URL}/profile_icon.png`} alt="icone de perfil" onClick={() => handleUserProfile(index)} />
                        </div>
                        
                    )
                })}
                <div className="flex flex-col items-center gap-5 ">
                    <p className="text-[25px]">Novo Perfil</p> 
                    <img className="w-40" src = {`${process.env.PUBLIC_URL}/adicionar_perfil.png`} alt="icone de perfil" />
                </div>
            </div>
            {/* botão de adicionar perfil */}
            
        </div>
    );
}
export default Profile;