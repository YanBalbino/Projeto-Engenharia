
import { CatalogRowProps } from '.././types';
import React, { useState,useRef } from 'react';
import WatchListCard from './watchListCard';


const WatchList: React.FC<CatalogRowProps> = ({gender,movies}) => {
   
    const windowWidth = window.innerWidth
    const moviesPerLine = Math.floor(windowWidth/200) -2


    return (
        <div className='relative  flex flex-col mr-22'>
            <h1 className=" ml-5 text-4xl" >{gender}</h1> 
            <div className='flex flex-row  gap-5 items-center'>
                <div  className=" mt-4 mb-4 ml-auto mr-auto w-11/12 grid grid-cols-10 justify-start gap-5  " > 
                    {movies.map((movie,index) => {
                        let lastCard;
                        let firstCard = false;
                        if((index+1) % moviesPerLine == 0){
                            lastCard = true;
                        }
                        else{
                            lastCard = false
                            if((index+1) % moviesPerLine == 1){
                                firstCard = true;
                             }
                             else{
                                firstCard = false;
                             }
                        }
                        return(
                        <WatchListCard key ={index} 
                            title={movie.title}
                            description={movie.description} 
                            releaseYear={movie.releaseYear}
                            duration={movie.duration}
                            pictureUrl={movie.pictureUrl}
                            last ={lastCard}
                            first = {firstCard}
                        />
                        )
                    })}
                </div>
            </div>
            
        </div>
    )
}

export default WatchList;