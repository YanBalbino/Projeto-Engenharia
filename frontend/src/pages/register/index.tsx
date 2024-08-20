import RegisterInput from "./registerInput";
import React, { useState } from 'react';
import { ArrowLeftOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom"

const Register = () =>{
    const navigate = useNavigate()
    const handleClick = (rota:string) =>{
   
        navigate(rota)
    }
    
    const [vals, setVals] = useState({
        val1: '',
        val2: '',
        val3: '',
    });

    const handleVal = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        if (/^\d*$/.test(value)) {
            setVals(prevInputs => ({
                ...prevInputs,
                [id]: value
            }));
        }
    };
    const [currentForm,setCurrentForm] = useState<'form1' | 'form2'>('form1');
    const handleCurrentForm = () =>{
        setCurrentForm(currentForm === 'form1' ? 'form2' : 'form1');
    }
   
    return(
        <div className="text-white font-inter  w-screen h-screen bg-gradient-to-b from-black to-cyan-950 flex flex-col pt-5 items-center  gap-4 ">
            <h1 className=" text-6xl">StreamIt!</h1>
            
            {currentForm === 'form1' && (
                
                <div className=" w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
                    <ArrowLeftOutlined onClick={() => handleClick('/login')} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
                    <h2 className="text-3xl">Criar conta</h2>
                    <RegisterInput name="Email" type = "text" />
                    <RegisterInput name = "Nome de usuário" type = "text" />
                    <RegisterInput name = "senha" type = "password"/>
                    <button onClick={handleCurrentForm}  className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
                </div>
            )}
            {currentForm === 'form2' && (
                
            <div className=" w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
                <ArrowLeftOutlined onClick={handleCurrentForm} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
                <h2 className="text-3xl">Forma de pagamento</h2>
                <div className=" w-full flex flex-col justify-center gap-4">
                    <div className="flex flex-row gap-1">
                        <input type="radio" name="pay"/>
                        <label htmlFor="">Cartão de crédito</label>
                    </div>
                    <div className="  w-full flex flex-row flex-wrap items-center gap-5">
                        <div className=" w-3/5 flex flex-col">
                            <label htmlFor="">Agência</label>
                            <input type="text" name="" id="" className="  h-10 rounded-lg text-black p-2" />
                        </div>
                        <div className=" w-2/6 flex flex-col">
                            <label htmlFor="">Validade</label>
                            <div className="flex flex-row items-center gap-3">
                                <input type="text" value={vals.val1} onChange={handleVal}  name="" id="val1" maxLength={2}  className=" w-2/4 h-10 rounded-lg text-black p-2" />
                                <p className="text-xl">/</p>
                                <input type="text" value={vals.val2} onChange={handleVal} name="" id="val2" maxLength={2} className=" w-2/4 h-10 rounded-lg text-black p-2" />
                            </div>
                        </div>
                        <div className="  w-full flex flex-row flex-wrap items-center gap-5">
                            <div className=" w-3/5 flex flex-col">
                                <label htmlFor="">Número da conta</label>
                                <input type="text" name="" id="" className="  h-10 rounded-lg text-black p-2" />
                            </div>
                            <div className=" w-2/6 flex flex-col">
                                <label htmlFor=""> Código de seg.</label>
                                <input type="text" value={vals.val3} onChange={handleVal} name="" id="val3" maxLength={3} className="  h-10 rounded-lg text-black p-2" />
                            </div>
                        </div>
                        
                    </div>
                    <div className="flex flex-col ">
                        <div className="flex flex-row gap-1">
                            <input type="radio" name="pay" />
                            <label htmlFor="">Boleto</label>    
                        </div>
                        <p className=" ml-2 text-sm">*Será enviado o pdf do boleto para o email cadastrado</p>
                    </div>
                    
                </div>
                <button   className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Criar conta</button>
            </div>
            )}
             
        </div>
    )
}

export default Register;