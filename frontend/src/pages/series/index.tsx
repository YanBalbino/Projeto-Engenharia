import { Movie } from "../catalog/types";
import CatalogRow from "../catalog/catalogRow";

const Series = () => {
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

    const mainMovie = [
        {title: "Série 1",
        description: "Um jovem é chamado para salvar seu reino de um tirano, embarcando em uma jornada épica cheia de magia e mistério.",
        releaseYear: "2020",
        duration: "3 temporadas",
        pictureUrl: "altBatPost.jpg"}
    ]
    
    return (
        <div id="content" className="px-10 flex flex-col gap-7">
            <div id="mainMovieInfo" className="flex flex-row">
                <div id="movieText" className="flex flex-col rounded-xl w-2/5 gap-4 px-5 py-20 z-20 justify-content-center bg-black bg-opacity-15">
                    <p id="mainMovieTitle" className="text-2xl">{mainMovie[0].title}</p>
                    <p id="mainMovieDescription" className="text-base w-3/5">{mainMovie[0].description}<br></br><br></br>Lançamento: {mainMovie[0].releaseYear}<br></br>Duração: {mainMovie[0].duration}</p>
                </div>
                <img 
                src={mainMovie[0].pictureUrl} 
                alt="Main Movie" 
                className="h-max-1/4 w-2/4 rounded-xl shadow-2xl shadow-slate-900"/>
            </div>
            <div id="Movies">
                <CatalogRow gender={"Ação"} movies={moviesAcao} />
                <CatalogRow gender={"Fiçção"} movies={moviesAcao} />
                <CatalogRow gender={"Romance"} movies={moviesAcao} />
            </div>
            
        </div>
    );
}

export default Series;