import { useNavigate } from "react-router-dom";
import { useState } from "react";
import PaymentCard from "./payCard";

const ConfigPayment = () => {

    const navigate = useNavigate();
    const [isAtual, setIsAtual] = useState(false);
    
    const handleIsAtual = () => {
        setIsAtual(!isAtual);
    }

    return (
        <div id="background" className="w-screen h-screen bg-gradient-to-b from-[#000713] to-[#2C4167] text-white flex flex-col flex-wrap gap-16">
            <h1 
                className="self-start justify-self-start pl-16 pt-5 text-5xl hover:cursor-pointer" 
                onClick={() => navigate('/catalog')}>Stream it!
            </h1>
            <div id="content" className="flex flex-col items-center content-center gap-4">
                
                <h1 className="text-4xl">Configurações de Pagamento</h1>
                <h2 className="text-lg">Edite a forma de pagamento preferida ou as informações de pagamento</h2>
                <PaymentCard/>
            </div>
        </div>
    )
}
export default ConfigPayment;