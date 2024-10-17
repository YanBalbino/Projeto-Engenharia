import CatalogCard from '../catalog/catalogCard'
import { useState,useEffect } from 'react';
import fetchFilmsByGenre from '../../services/filmService';
import { ReturnFilmDTO,Pageable } from '../../utils/types';

const genreTranslations: { [key: number]: string } = {
    0: "Genre",
    1: "Action" ,
    2: "Adventure" ,
    3: "Comedy" ,
    4: "Drama" ,
    5: "Science Fiction" ,
    6: "Horror" ,
    7: "Thriller"
};

const Search = () => {

    const [selectedFilters,setSelectedFilters] = useState({
        genre: 0,
        type: 0,
    })
    const [media,setMedia] = useState<Pageable<ReturnFilmDTO> | null>(null);

    const handleFilterChange = (filterName:string) => (event: React.ChangeEvent<HTMLSelectElement>) => {
        const { value } = event.target;
        
         setSelectedFilters((prev) => ({
            ...prev,
            [filterName]: value 
        }));

    };

    useEffect(() => {
        const search = async () => {
            console.log(selectedFilters);

            if (selectedFilters.type == 1) {
                if(selectedFilters.genre !=0){
                    const genre: number = selectedFilters.genre;
                    const responseData:Pageable<ReturnFilmDTO> = await fetchFilmsByGenre(genreTranslations[genre]);
                    if(responseData){
                        setMedia(responseData)
                    }
                    
                }
            } else if (selectedFilters.type == 2) {
                
            } else {
                setMedia(null)
            }
        };

        
        if (selectedFilters.type) {
            search();
        }
    }, [selectedFilters]);
    const cardInfos = [
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: true
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: true,
            first: false
        }];

    const filters = [
        {
            name: "genre",
            options: [
                {id: 0, name: "Gênero"},
                {id: 1, name: "Ação"},
                {id: 2, name: "Aventura"},
                {id: 3, name: "Comédia"},
                {id: 4, name: "Drama"},
                {id: 5, name: "Ficção Científica"},
                {id: 6, name: "Terror"},
                {id: 7, name: "Suspense"}
            ]
        },
        {
            name: "Qualidade",
            options: [
                {id: 0, name: "Qualidade"},
                {id: 1, name: "HD"},
                {id: 2, name: "Full HD"},
                {id: 3, name: "4K"}
            ]
        },
        {
            name: "type",
            options: [
                {id: 0, name: "Tipo de conteúdo"},
                {id: 1, name: "Filme"},
                {id: 2, name: "Série"}
            ]
        }
    ]

    const generateFilters = () => {
        return(
            <div id="filters" className="flex flex-row gap-5">
                {filters.map(filter => {
                    return (
                        <div id={filter.name} className="flex flex-col">
                            <select className="rounded-xl bg-slate-800 opacity-90 h-10 pl-3 text-white text-base" onChange={handleFilterChange(filter.name)}>
                                {filter.options.map(option => {
                                    return <option key={option.id} value={option.id}>{option.name}</option>
                                })}
                            </select>
                        </div>
                    )
                })}
            </div>
        )
    }

    return (
        <div id="content" className="pl-5 flex flex-col gap-10">
            <div id="searchContent" className="flex flex-col gap-4">
                <h1 className="text-4xl">Pesquisar no catálogo</h1>
                <input 
                    type="text" 
                    autoFocus
                    placeholder="Pesquisar" 
                    className="rounded-xl bg-gray-300 w-3/4 h-12 pl-3 text-black text-lg"/>
                <div id="filters" className="flex flex-row gap-10">
                    {generateFilters()}
            </div>    
            </div>
            
            

            <div id="searchCards" className="flex flex-row  flex-wrap gap-5 justify-evenly">
                   {media?.content.map((movie,index) =>{
                    return(
                        <CatalogCard key ={index} 
                            title={movie.media.titulo}
                            description={movie.media.descricao} 
                            releaseYear={movie.media.anoProducao}
                            duration={movie.duracao}
                            pictureUrl={movie.media.imgUrl}
                            
                        />
                    )
                   })}
            </div>
        </div>
    )
}
export default Search;