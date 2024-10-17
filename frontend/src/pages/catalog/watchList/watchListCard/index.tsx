import { useState } from "react";
import {Image } from 'antd';
import '../.././card.css'
import { CardProps } from "../../../../utils/types";

const WatchListCard: React.FC<CardProps> = ({title,description,releaseYear,duration,pictureUrl,last}) => {
    const [selected,setSelected] = useState(false)
    const handleSelected = () =>{
        setSelected(!selected)
    }
    return (
        <div onMouseEnter={handleSelected} onMouseLeave={handleSelected} className={`  mb-10 h-52 col-span-2 bg-slate-500 border-2 border-red-200 rounded-xl flex ${last ? 'flex-row-reverse ' : 'flex-row'}  transition-all duration-300   ${selected ? 'min-w-[400px] max-w-[400px]  overflow-hidden z-10 ' : 'min-w-[200px] max-w-[200px] z-0'}`}   >
            
            
            <div >
                <Image 
                width='197px'
                height='204px'
                src={`${process.env.PUBLIC_URL}/batPost.jpg`} 
                alt="a" 
                preview={false} 
                className={`  object-cover  ${selected ? 'rounded-l-xl' : 'rounded-xl'} `}
                />
            </div>
            
           
        
            {selected && (
                
                <div className="fade-in">  
                    <h1 className="ml-1">{title}</h1>
                    <p className=" ml-3">{description}</p>
                </div>
                
            )}

        </div>
    )
}

export default WatchListCard;