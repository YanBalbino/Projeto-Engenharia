import React, { useState } from 'react';
import Navbar from "./navbar";
import CatalogRow from "./catalogRow";
import { Movie } from "./types";
import WatchList from './watchList/index';
import Profile from '../profile';
import Search from '../search';

const moviesAcao: Movie[] = [
    {
        title: "Filme 1",
        description: "Em uma missão mortal para salvar um refém, um mercenário ousado enfrenta seu passado sombrio e desafiadores inimigos.",
        releaseYear: "2021",
        duration: "1h 58min",
        pictureUrl: "batPost.jpg"
    },
    {
        title: "Filme 2",
        description: "Um jovem é chamado para salvar seu reino de um tirano, embarcando em uma jornada épica cheia de magia e mistério.",
        releaseYear: "2019",
        duration: "2h 10min",
        pictureUrl: "../assets/guerreiro.png"
    },
    {
        title: "Filme 3",
        description: "Em uma expedição intergaláctica, um grupo de astronautas descobre segredos que mudarão para sempre o destino da humanidade.",
        releaseYear: "2022",
        duration: "2h 22min",
        pictureUrl: "../assets/espacial.png"
    },
    {
        title: "Filme 4",
        description: "Um detetive enfrentando seus próprios demônios se envolve em uma complexa teia de conspirações enquanto tenta desvendar um crime.",
        releaseYear: "2018",
        duration: "1h 50min",
        pictureUrl: "../assets/detetive.png"
    },
    {
        title: "Filme 5",
        description: "Dois jovens de mundos diferentes lutam contra as adversidades para manter seu amor em meio a uma sociedade rígida e preconceituosa.",
        releaseYear: "2020",
        duration: "1h 42min",
        pictureUrl: "../assets/amor.png"
    },
    {
        title: "Filme 6",
        description: "Forças do bem e do mal colidem em uma batalha épica pelo destino do mundo, com heróis improváveis surgindo para salvar o dia.",
        releaseYear: "2021",
        duration: "2h 15min",
        pictureUrl: "../assets/batalha.png"
    },
    {
        title: "Filme 7",
        description: "Um grupo de amigos se vê preso em uma floresta onde estranhos eventos começam a acontecer, testando sua coragem e amizade.",
        releaseYear: "2017",
        duration: "1h 35min",
        pictureUrl: "../assets/floresta.png"
    },
    {
        title: "Filme 8",
        description: "Em uma cidade dominada pelo crime, um ladrão habilidoso busca uma vida melhor, mas é puxado de volta para o mundo do crime.",
        releaseYear: "2019",
        duration: "1h 48min",
        pictureUrl: "../assets/cidade.png"
    },
    {
        title: "Filme 9",
        description: "Um policial cibernético luta para proteger a cidade dos criminosos que dominam as ruas à noite, enquanto busca sua própria humanidade.",
        releaseYear: "2020",
        duration: "2h 5min",
        pictureUrl: "../assets/robo.png"
    },
    {
        title: "Filme 10",
        description: "Após uma catástrofe global, os poucos sobreviventes devem lutar por recursos escassos enquanto enfrentam novos e terríveis perigos.",
        releaseYear: "2022",
        duration: "2h 12min",
        pictureUrl: "../assets/sobreviventes.png"
    },
    {
        title: "Filme 11",
        description: "Em uma missão mortal para salvar um refém, um mercenário ousado enfrenta seu passado sombrio e desafiadores inimigos.",
        releaseYear: "2021",
        duration: "1h 58min",
        pictureUrl: "../assets/resgate.png"
    },
    {
        title: "Filme 12",
        description: "Um jovem é chamado para salvar seu reino de um tirano, embarcando em uma jornada épica cheia de magia e mistério.",
        releaseYear: "2019",
        duration: "2h 10min",
        pictureUrl: "../assets/guerreiro.png"
    },
    {
        title: "Filme 13",
        description: "Em uma expedição intergaláctica, um grupo de astronautas descobre segredos que mudarão para sempre o destino da humanidade.",
        releaseYear: "2022",
        duration: "2h 22min",
        pictureUrl: "../assets/espacial.png"
    },
    {
        title: "Filme 14",
        description: "Um detetive enfrentando seus próprios demônios se envolve em uma complexa teia de conspirações enquanto tenta desvendar um crime.",
        releaseYear: "2018",
        duration: "1h 50min",
        pictureUrl: "../assets/detetive.png"
    },
    {
        title: "Filme 15",
        description: "Dois jovens de mundos diferentes lutam contra as adversidades para manter seu amor em meio a uma sociedade rígida e preconceituosa.",
        releaseYear: "2020",
        duration: "1h 42min",
        pictureUrl: "../assets/amor.png"
    },
    {
        title: "Filme 16",
        description: "Forças do bem e do mal colidem em uma batalha épica pelo destino do mundo, com heróis improváveis surgindo para salvar o dia.",
        releaseYear: "2021",
        duration: "2h 15min",
        pictureUrl: "../assets/batalha.png"
    },
    {
        title: "Filme 17",
        description: "Um grupo de amigos se vê preso em uma floresta onde estranhos eventos começam a acontecer, testando sua coragem e amizade.",
        releaseYear: "2017",
        duration: "1h 35min",
        pictureUrl: "../assets/floresta.png"
    },
    {
        title: "Filme 18",
        description: "Em uma cidade dominada pelo crime, um ladrão habilidoso busca uma vida melhor, mas é puxado de volta para o mundo do crime.",
        releaseYear: "2019",
        duration: "1h 48min",
        pictureUrl: "../assets/cidade.png"
    },
    {
        title: "Filme 19",
        description: "Um policial cibernético luta para proteger a cidade dos criminosos que dominam as ruas à noite, enquanto busca sua própria humanidade.",
        releaseYear: "2020",
        duration: "2h 5min",
        pictureUrl: "../assets/robo.png"
    },
    {
        title: "Filme 20",
        description: "Após uma catástrofe global, os poucos sobreviventes devem lutar por recursos escassos enquanto enfrentam novos e terríveis perigos.",
        releaseYear: "2022",
        duration: "2h 12min",
        pictureUrl: "../assets/sobreviventes.png"
    }
];

const Catalog = () => {
  // Estado movido para o componente pai
  const [navbarSelect, setNavbarSelect] = useState(1);

  const handleNavbar = (n: number) => {
    setNavbarSelect(n);
  };

  return (
    <div id="background" className="pt-5 text-white font-inter w-screen h-screen bg-gradient-to-b from-[#000713] to-[#2C4167] flex flex-col gap-10 overflow-x-hidden ">
        <Navbar navbarSelect={navbarSelect} onNavbarSelect={handleNavbar} /> 

        {/* home */}
        {navbarSelect == 1 && (
        <div>
            <CatalogRow gender="Ação" movies={moviesAcao} />
            <CatalogRow gender="Ficção" movies={moviesAcao} />
            <CatalogRow gender="Romance" movies={moviesAcao} />
        </div>
        )}

         {/* filmes */}
        {navbarSelect == 2 && (
            <div>
                <h1>uau</h1>
            </div>)}
        
         {/* series */}
        {navbarSelect == 3 && (
            <div>
                <h1>uau</h1>
            </div>)}
        
         {/* busca */}
        {navbarSelect == 4 && (
            <div>
                <Search/>
            </div>
            )}
        
         {/* menu hamburguer */}
        {navbarSelect == 5 && (
            <div>
                <h1>uau</h1>
            </div>)}
        
         {/* watchlist */}
        {navbarSelect == 6 &&(
            <div>
                <WatchList gender = "Watchlist" movies={moviesAcao}/>
            </div>
        )}

         {/* configs */}
        {navbarSelect == 7 && (
            <Profile/>
        )}
      
      
    </div>
  );
};

export default Catalog;
