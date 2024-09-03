import { useState } from "react";
import {Image } from 'antd';
import './card.css'
interface CatalogCardProps{
    title: string; 
    description: string; 
    releaseYear: string;
    duration:string;
    pictureUrl:string;

}


const CatalogCard: React.FC<CatalogCardProps> = ({title,description,releaseYear,duration,pictureUrl}) => {
    const [selected,setSelected] = useState(false)
    const handleSelected = () =>{
        setSelected(!selected)
    }
    return (
        <div onMouseEnter={handleSelected} onMouseLeave={handleSelected} className={`mb-10 h-52 bg-slate-500 border-2 border-red-200 rounded-xl flex flex-row transition-all duration-300   ${selected ? 'min-w-[400px] scale-105' : 'min-w-[200px]'}`}   >
            
            <div>
                <Image 
                width='197px'
                height='204px'
                src={`${process.env.PUBLIC_URL}/batPost.jpg`} 
                alt="a" 
                preview={false} 
                className={`h-full object-cover  ${selected ? 'rounded-l-xl' : 'rounded-xl'} `}
                />
            </div>
           
        
            {selected && (
                <div className="fade-in">  
                    <h1>{title}</h1>
                    <p>{description}</p>
                </div>
                
            )}

        </div>
    )
}

export default CatalogCard;