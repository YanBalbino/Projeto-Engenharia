import CatalogCard from '../catalog/catalogCard'
import { useState } from 'react';


const Search = () => {

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
            name: "Gênero",
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
            name: "Tipo de conteúdo",
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
                            <select className="rounded-xl bg-gray-400 h-10 pl-3 text-black text-base">
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
            
            

            <div id="searchCards" className="flex flex-row gap-5 justify-evenly">
                    {/* cards exemplo, os filtros devem ser aplicados com base no input de pesquisa */}
                    <CatalogCard 
                    title={cardInfos[0].title} 
                    description={cardInfos[0].description} 
                    releaseYear={cardInfos[0].releaseYear} 
                    duration={cardInfos[0].duration} 
                    pictureUrl={cardInfos[0].pictureUrl} 
                    last={cardInfos[0].last} 
                    first={cardInfos[0].first}/>
                    <CatalogCard 
                    title={cardInfos[1].title} 
                    description={cardInfos[1].description} 
                    releaseYear={cardInfos[1].releaseYear} 
                    duration={cardInfos[1].duration} 
                    pictureUrl={cardInfos[1].pictureUrl} 
                    last={cardInfos[1].last} 
                    first={cardInfos[1].first}/>
                    <CatalogCard 
                    title={cardInfos[2].title} 
                    description={cardInfos[2].description} 
                    releaseYear={cardInfos[2].releaseYear} 
                    duration={cardInfos[2].duration} 
                    pictureUrl={cardInfos[2].pictureUrl} 
                    last={cardInfos[2].last} 
                    first={cardInfos[2].first}/>
                    <CatalogCard 
                    title={cardInfos[3].title} 
                    description={cardInfos[3].description} 
                    releaseYear={cardInfos[3].releaseYear} 
                    duration={cardInfos[3].duration} 
                    pictureUrl={cardInfos[3].pictureUrl} 
                    last={cardInfos[3].last} 
                    first={cardInfos[3].first}/>
                    <CatalogCard 
                    title={cardInfos[4].title} 
                    description={cardInfos[4].description} 
                    releaseYear={cardInfos[4].releaseYear} 
                    duration={cardInfos[4].duration} 
                    pictureUrl={cardInfos[4].pictureUrl} 
                    last={cardInfos[4].last} 
                    first={cardInfos[4].first}/>
            </div>
        </div>
    )
}
export default Search;