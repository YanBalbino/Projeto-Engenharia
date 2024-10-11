import { useState,useRef } from "react";
import {Image } from 'antd';
import '.././card.css'
import cancelIcon from '../../../images/x_cancel.png';
import { CardProps } from ".././types";


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
                <div>
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
                <div
                    className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50"
                    onClick={handleCloseModal} // Fecha o modal ao clicar fora dele
                >
                    <div
                        className="bg-gray-800 p-5 rounded-2xl flex flex-row "
                        onClick={(e) => e.stopPropagation()} // Impede o clique dentro do modal de fechar
                    >
                        <div>
                        <Image
                            src={`${process.env.PUBLIC_URL}/batPost.jpg`}
                            alt="a"
                            preview={false}
                            className="rounded-xl" // Mantém a borda arredondada
                            style={{ width: '400px', height: '500px' }} // Força a imagem a se ajustar ao tamanho especificado
                        />
                        </div>
                        <div className="w-[700px] mx-8">
                            <h1 className="font-bold text-5xl mb-10">{title}</h1>
                            <p className="text-[20px] text-justify">{description}</p>
                        </div>
                        <img className="ml-6 w-[30px] h-[30px] cursor-pointer" onClick={handleCloseModal} src={cancelIcon} alt="Close" />
                    </div>
                </div>
            )}
        </>
    );
};

export default CatalogCard;
