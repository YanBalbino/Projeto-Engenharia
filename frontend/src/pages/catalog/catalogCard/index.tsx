import { useState,useRef } from "react";
import {Image } from 'antd';
import '.././card.css'
import cancelIcon from '../../../images/x_cancel.png';
import { CardProps } from ".././types";
import ModalFilme from "./ModalFilme";
import ModalSerie from "./ModalSerie";


const CatalogCard: React.FC<CardProps> = ({ title, description, releaseYear, duration, pictureUrl, last, first }) => {
    const [selected, setSelected] = useState(false);
    const [clicked, setClicked] = useState(false);
    const [showModal, setShowModal] = useState(false); // Estado para controlar o pop-up
    const cardRef = useRef<HTMLDivElement>(null);

    const handleSelected = () => {
        setSelected(!selected);
    };

    const handleClicked = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false); // Fecha o modal
    };

    return (
        <>
            <div
                ref={cardRef}
                onMouseEnter={handleSelected}
                onMouseLeave={handleSelected}
                onClick={handleClicked}
                className={`mb-10 h-52 bg-slate-500 border-2 border-red-200 rounded-xl flex flex-row transition-all duration-300 
                    ${selected ? 'min-w-[400px] max-w-[400px] scale-105 overflow-hidden' : 'min-w-[200px] max-w-[200px]'}`}
            >
                <div className="cursor-pointer">
                    <Image
                        width='197px'
                        height='204px'
                        src={`${process.env.PUBLIC_URL}/batPost.jpg`}
                        alt="a"
                        preview={false}
                        className={`object-cover ${selected ? 'rounded-l-xl' : 'rounded-xl'}`}
                    />
                </div>

                {selected && (
                    <div className="fade-in">
                        <h1 className="ml-1">{title}</h1>
                        <p className="ml-3">{description}</p>
                    </div>
                )}
            </div>

            {showModal && (
                // <ModalFilme handleCloseModal={handleCloseModal} title={title} description={description} releaseYear={releaseYear} duration={duration} cancelIcon={cancelIcon}/>
                <ModalSerie handleCloseModal={handleCloseModal} title={title} description={description} releaseYear={releaseYear} duration={duration} cancelIcon={cancelIcon}/>
            )}
        </>
    );
};

export default CatalogCard;
