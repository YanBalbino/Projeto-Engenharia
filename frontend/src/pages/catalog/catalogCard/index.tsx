import { useState,useRef } from "react";
import {Image } from 'antd';
import '.././card.css'
import cancelIcon from '../../../images/x_cancel.png';
import { CardProps } from "../../../utils/types";
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
                className={`mb-10 h-52 bg-slate-500 border-2 border-red-200 rounded-xl flex flex-row transition-all duration-300 hover:scale-105`}
            >
                <div className="cursor-pointer">
                    <Image
                        width='197px'
                        height='204px'
                        src={pictureUrl}
                        alt="a"
                        preview={false}
                        className="rounded-xl"
                    />
                </div>

               
            </div>

            {showModal && (
                // <ModalFilme handleCloseModal={handleCloseModal} title={title} description={description} releaseYear={releaseYear} duration={duration} cancelIcon={cancelIcon}/>
                <ModalSerie handleCloseModal={handleCloseModal} title={title} description={description} releaseYear={releaseYear} duration={duration} cancelIcon={cancelIcon}/>
            )}
        </>
    );
};

export default CatalogCard;
