
import { CatalogRowProps } from '.././types';
import CatalogCard from '../catalogCard';
import React, { useState,useRef } from 'react';
import { ArrowLeftOutlined, ArrowRightOutlined} from "@ant-design/icons";


const CatalogRow: React.FC<CatalogRowProps> = ({gender,movies}) => {
    let cont = 0
    const windowWidth = window.innerWidth
    const moviesPerDiv = Math.round(windowWidth/200) 
    const divs  = Math.round(movies.length/moviesPerDiv)
    const [right,setRight] = useState(0);
    const rowRef = useRef<HTMLDivElement>(null);
  
    const scrollRight = () =>{

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
        <div className='relative  flex flex-col mr-22'>
            <h1 className=" ml-5 text-4xl" >{gender}</h1> 
            <div className='flex flex-row gap-5 items-center'>
                <ArrowLeftOutlined onClick={scrollLeft} className=" ml-2 transform -translate-y-1/2 scale-125 rounded-full hover:cursor-pointer transition-transform hover:scale-150  hover:bg-white hover:text-black" />
                <div ref = {rowRef} className=" mt-4 mb-4 pt-5 pl-5 w-11/12 flex flex-row justify-start gap-5  overflow-hidden" > 
                    {movies.map((movie,index) => {
                        
                        return(
                        <CatalogCard key ={index} 
                            title={movie.title}
                            description={movie.description} 
                            releaseYear={movie.releaseYear}
                            duration={movie.duration}
                            pictureUrl={movie.pictureUrl}
                        />
                        )
                    })}
                </div>
                 <ArrowRightOutlined onClick={scrollRight}   className=" mr-10 transform -translate-y-1/2 scale-125 rounded-full hover:cursor-pointer transition-transform hover:scale-150 hover:bg-white hover:text-black" /> 
            </div>
            
        </div>
    )
}

export default CatalogRow;