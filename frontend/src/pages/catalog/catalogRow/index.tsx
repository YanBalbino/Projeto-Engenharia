
import { CatalogRowProps,ReturnFilmDTO,Pageable } from '../../../utils/types';
import CatalogCard from '../catalogCard';
import React, { useState,useRef, useEffect } from 'react';
import { ArrowLeftOutlined, ArrowRightOutlined} from "@ant-design/icons";
import fetchFilmsByGenre from '../../../services/filmService';



const CatalogRow: React.FC<CatalogRowProps> = ({genre,genre_movie}) => {
    const [filmsPage, setFilmsPage] = useState<Pageable<ReturnFilmDTO> | null>(null);
    let cont = 0
    const windowWidth = window.innerWidth
    const moviesPerDiv = Math.round(windowWidth/200) -1
    const [divs,setDivs] = useState(0);
    const [right,setRight] = useState(0);
    const rowRef = useRef<HTMLDivElement>(null);
    let firsts = [];
    let lasts = [];
    
   
      useEffect(() => {
        const fetchData = async ()=>{
            const responseData: Pageable<ReturnFilmDTO> = await fetchFilmsByGenre(genre_movie)

            if(responseData){
                setFilmsPage(responseData);
            }
            setDivs(Math.round(5/moviesPerDiv))
        }
        
        fetchData();
      }, []);
   
  
    const scrollRight = () =>{

        console.log(divs)
        const row = rowRef.current;
        if(row){
            
            row.scrollBy({left:(row.scrollWidth/divs),behavior: 'smooth'})
            setRight(right + 1)
        }
    }
    const scrollLeft = () =>{
        const row = rowRef.current;
        if(row){
            
            row.scrollBy({left:-windowWidth,behavior: 'smooth'})
            setRight(right - 1)
        }
    }
   
    
    return (
        <div className='relative  flex flex-col mr-22 z-10'>
            <h1 className=" ml-5 text-4xl" >{genre}</h1> 
            <div className='flex flex-row gap-5 items-center'>
                <ArrowLeftOutlined onClick={scrollLeft} className={`${right >0 ? 'opacity-100' : 'opacity-0'} ml-2 transform -translate-y-1/2 scale-125 rounded-full  hover:cursor-pointer transition-transform hover:scale-150  hover:bg-white hover:text-black`} />
                <div ref = {rowRef} className=" mt-4 mb-4 pt-5 pl-5 w-11/12 flex flex-row justify-start gap-5  overflow-hidden" > 
                    {filmsPage?.content.map((movie,index) => {
                         let lastCard;
                         let firstCard = false;
                         if((index+1) % moviesPerDiv == 0){
                             lastCard = true;
                             lasts.push(index)
                         }
                         else{
                             lastCard = false
                             if((index+1) % moviesPerDiv == 1){
                                firstCard = true;
                             }
                             else{
                                firstCard = false;
                             }
                         }
                         
                        return(
                        <CatalogCard key ={index} 
                            title={movie.media.titulo}
                            description={movie.media.descricao} 
                            releaseYear={movie.media.anoProducao}
                            duration={movie.duracao}
                            pictureUrl={movie.media.imgUrl}
                            last={lastCard}
                            first={firstCard}
                        />
                        )
                    })}
                </div>
                 <ArrowRightOutlined onClick={scrollRight}   className={` mr-10 transform -translate-y-1/2 scale-125 rounded-full hover:cursor-pointer transition-transform hover:scale-150 hover:bg-white hover:text-black`} /> 
            </div>
            
        </div>
    )
}

export default CatalogRow;