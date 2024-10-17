import { Image } from 'antd';

interface ModalSerieProps {
    handleCloseModal: () => void;
    title: string;
    description: string;
    releaseYear: string;
    duration: string;
    cancelIcon: string;
}

const ModalSerie: React.FC<ModalSerieProps> = ({ handleCloseModal, title, description, releaseYear, duration, cancelIcon }) => {
    return (
        <div
            className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50"
            onClick={handleCloseModal} // Fecha o modal ao clicar fora dele
        >
            <div
                className="bg-gray-800 p-5 rounded-2xl flex flex-row"
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
                    <p className="text-[20px] text-justify mt-6">{releaseYear} - {duration}</p>
                </div>
                <img className="ml-6 w-[30px] h-[30px] cursor-pointer" onClick={handleCloseModal} src={cancelIcon} alt="Close" />
            </div>
        </div>
    );
}

export default ModalSerie;
