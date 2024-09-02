// src/components/Register.js

import React, { useState, useRef } from 'react';
import { ArrowLeftOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";


const Register = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    senha: '',
    metodoPagamento: '',
    agencia: '',
    validade: '',
    numeroConta: '',
    codigoSeguranca: ''
  });

  
  const [vals, setVals] = useState({
    val1: '',
    val2: '',
    val3: ''
  });

 
  const [currentForm, setCurrentForm] = useState<'form1' | 'form2'>('form1');

  const [errors, setErrors] = useState({
    nome: '',
    email: '',
    senha: '',
    pagamento:'',
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

 
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

 
  const handleCurrentForm = () => {
    setCurrentForm(currentForm === 'form1' ? 'form2' : 'form1');
  };

  const getCurrentDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };
 
  const handleSubmit = async () => {
    
    const data = {
    email: formData.email,
    nome: formData.nome,
    senha: formData.senha,
    perfis: [
        {
        nome: "User's Profile",
        iconUrl: "https://example.com/images/icon1.png",
        perfilInfantil: false,
        generosPreferidos: ["Action", "Drama"]
        }
    ],
    metodoPagamento: formData.metodoPagamento,
    dataPagamento: getCurrentDate(),
    valor: 30,
    };

    try {
    const response = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });

    if (!response.ok) {
        const errorData = await response.json();
        console.log(errorData);
        if (errorData.errors) {
            setErrors({
                nome: errorData.errors.find((msg:string) => msg.includes('Nome')) || '',
                email: errorData.errors.find((msg:string) => msg.includes('E-mail')) || '',
                senha: errorData.errors.find((msg:string) => msg.includes('Senha')) || '',
                pagamento: errorData.errors.find((msg:string) => msg.includes('Pagamento')) || '',
               
            });
        }
        setCurrentForm('form1'); 
    } else {

        navigate('/login')
    }
    } catch (error) {
    console.error('Erro ao criar o usuário:', error);
    }
    
  };

  return (
    <div className="text-white font-inter w-screen h-screen bg-gradient-to-b from-black to-cyan-950 flex flex-col pt-5 items-center gap-4 ">
      <h1 className="text-6xl">StreamIt!</h1>
      
        {currentForm === 'form1' && (
          <div className="w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
            <ArrowLeftOutlined onClick={() => navigate('/login')} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
            <h2 className="text-3xl">Criar conta</h2>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor='email' className=" ml-2">Email</label>
                <input type="text" name="email" id="email" onChange={handleChange} className=" h-10 rounded-lg text-black p-2" value={formData.email} />
                {errors.email && <p className="text-red-500">{errors.email}</p>}
            </div>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor="nome" className=" ml-2">Nome de usuário</label>
                <input type="text" name="nome" id="nome" onChange={handleChange} className=" h-10 rounded-lg text-black p-2" value={formData.nome}/>
                {errors.nome && <p className="text-red-500">{errors.nome}</p>}
            </div>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor="senha" className=" ml-2">Senha</label>
                <input type="password" name="senha" id="senha" onChange={handleChange} className=" h-10 rounded-lg text-black p-2" value={formData.senha}/>
                {errors.senha && <p className="text-red-500">{errors.senha}</p>}
            </div>
            <button onClick={handleCurrentForm} type="button" className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
          </div>
        )}
        {currentForm === 'form2' && (
          <div className="w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
            <ArrowLeftOutlined onClick={handleCurrentForm} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
            <h2 className="text-3xl">Forma de pagamento</h2>
            {errors.pagamento && <p className="text-red-500">{errors.pagamento}</p>}
            <div className="w-full flex flex-col justify-center gap-4">
              <div className="flex flex-row gap-1">
                <input type="radio" name="metodoPagamento" value="Credit Card" onChange={handleChange}  />
                <label>Cartão de crédito</label>
              </div>
              <div className="w-full flex flex-row flex-wrap items-center gap-5">
                <div className="w-3/5 flex flex-col">
                  <label>Agência</label>
                  <input type="text" name="agencia" value={formData.agencia} onChange={handleChange} className="h-10 rounded-lg text-black p-2"  />
                </div>
                <div className="w-2/6 flex flex-col">
                  <label>Validade</label>
                  <div className="flex flex-row items-center gap-3">
                    <input type="text" value={vals.val1} onChange={handleVal} name="validade" id="val1" maxLength={2} className="w-2/4 h-10 rounded-lg text-black p-2" />
                    <p className="text-xl">/</p>
                    <input type="text" value={vals.val2} onChange={handleVal} name="validade" id="val2" maxLength={2} className="w-2/4 h-10 rounded-lg text-black p-2" />
                  </div>
                </div>
                <div className="w-full flex flex-row flex-wrap items-center gap-5">
                  <div className="w-3/5 flex flex-col">
                    <label>Número da conta</label>
                    <input type="text" name="numeroConta" value={formData.numeroConta} onChange={handleChange} className="h-10 rounded-lg text-black p-2" />
                  </div>
                  <div className="w-2/6 flex flex-col">
                    <label>Código de seg.</label>
                    <input type="text" value={vals.val3} onChange={handleVal} name="codigoSeguranca" id="val3" maxLength={3} className="h-10 rounded-lg text-black p-2" />
                  </div>
                </div>
              </div>
              <div className="flex flex-col">
                <div className="flex flex-row gap-1">
                  <input type="radio" name="metodoPagamento" value="ticket" onChange={handleChange} />
                  <label>Boleto</label>
                </div>
                <p className="ml-2 text-sm">*Será enviado o pdf do boleto para o email cadastrado</p>
              </div>
            </div>
            <button onClick={handleSubmit} type="button" className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Criar conta</button>
          </div>
        )}
    </div>
  );
};

export default Register;
