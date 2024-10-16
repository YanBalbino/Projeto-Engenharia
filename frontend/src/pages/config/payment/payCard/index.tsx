import { useNavigate } from "react-router-dom";
import { useState } from 'react';

const PaymentCard  = () => {
    
    // simulando um json recebido pra essa página
    const cartaoInfo = [
        {   
            isPgAtual: true,
            numero: "1234 5678 1234 5678",
            validade: "12/23",
            nome: "Nome do Titular",
            codSeg: "123"
        }
    ]

    //TODO - implementar a função de edição e configurar endpoints

    const [numero, setNumero] = useState(cartaoInfo[0].numero);
    const [validade, setValidade] = useState(cartaoInfo[0].validade);
    const [nome, setNome] = useState(cartaoInfo[0].nome);
    const [codSeg, setCodSeg] = useState(cartaoInfo[0].codSeg);

    const handleCtInfo = (novoCt: any) => {
        setNumero(novoCt.numero);
        setValidade(novoCt.validade);
        setNome(novoCt.nome);
        setCodSeg(novoCt.codSeg);
    }

    const preencherCartao = (cartaoInfo: any) => {
        return (
            console.log("brongens")
        );
    }

    const navigate = useNavigate();

    return(
        <div id="card" className="bg-zinc-700 bg-opacity-40 p-8 rounded-xl gap-3 border">
            <div className="flex flex-row gap-2 py-2">
                <input type="radio" checked disabled></input>
                <label>Cartão de crédito</label>
                <label className="text-gray-600"><i>{true ? '(Atual)' : ""}</i></label>
            </div>

            <div id="infosCartao" className="grid grid-cols-2 grid-rows-2 gap-3 grid-flow-row-dense items-center justify-items-center">
                    <div id="campoNumCartao" className="flex flex-col">
                        <label>Número do cartão</label>
                        <input 
                            type="text" 
                            className="rounded-lg text-black p-2" 
                            disabled
                            >
                        </input>
                    </div>
                    <div id="campoValCartao" className="flex flex-col">
                        <label>Validade</label>
                        <div id="validades" className="flex flex-row ">
                            <input 
                                type="text" 
                                id="mes" 
                                className="rounded-lg text-black p-2 max-w-12"
                                maxLength={2}
                                disabled>
                            </input>
                            <p className="text-xl pt-1">&ensp;/&ensp;</p>
                            <input 
                                type="text" 
                                id="ano" 
                                className="rounded-lg text-black p-2 max-w-12"
                                maxLength={2}
                                disabled>
                            </input>
                        </div>
                    </div>
                    <div id="campoNomeTitular" className="flex flex-col">
                        <label>Nome do titular</label>
                        <input type="text" className="rounded-lg text-black p-2" disabled></input>
                    </div>
                    <div id="campoCodSeg" className="flex flex-col">
                        <label>Código de seg.</label>
                        <input 
                            type="text" 
                            className="rounded-lg text-black p-2 max-w-12 justify-self-center" 
                            maxLength={3}
                            disabled>
                        </input>
                    </div>
            </div>
            {}

            <div id="infosBoleto" className="flex flex-row gap-2 pt-5">
                <input type="radio" disabled></input>
                <label>Boleto*</label>
                <label className="text-gray-600"><i>{false ? '(Atual)' : ""}</i></label>
            </div>
            <p className="text-sm">*Será enviado o pdf do boleto para o email cadastrado</p>
            <div id="botoes" className="pt-5 flex flex-row justify-evenly">
                <button 
                    type="button" 
                    className="bg-[#216EAD] hover:bg-sky-600 my-4 px-10 py-1 rounded-md">Editar
                </button>
                <button 
                    type="button" 
                    className="bg-[#216EAD] hover:bg-sky-600 my-4 px-10 py-1 rounded-md" 
                    onClick={() => navigate('../config')}>Confirmar
                </button>
            </div>
            
        </div>
    );
}

export default PaymentCard;