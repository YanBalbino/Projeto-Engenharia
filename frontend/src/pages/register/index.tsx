import React, { useState, useRef } from 'react';
import { ArrowLeftOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { Checkbox } from "antd";
import type { CheckboxProps } from 'antd';
import './checkbox.css'

const genders = [ 
  { label: 'Ação', value: 'acao' },
  { label: 'Aventura', value: 'aventura' },
  { label: 'Comédia', value: 'comedia' },
  { label: 'Drama', value: 'drama' },
  { label: 'Terror', value: 'terror' },
  { label: 'Suspense', value: 'suspense' },
  { label: 'Ficção Científica', value: 'ficcao_cientifica' },
  { label: 'Fantasia', value: 'fantasia' },
  { label: 'Romance', value: 'romance' },
  { label: 'Animação', value: 'animacao' },
  { label: 'Musical', value: 'musical' },
  { label: 'Documentário', value: 'documentario' },
  { label: 'Guerra', value: 'guerra' },
  { label: 'Policial', value: 'policial' },
  { label: 'Faroeste', value: 'faroeste' },
  { label: 'Esporte', value: 'esporte' },
  { label: 'Mistério', value: 'misterio' },
  { label: 'Biografia', value: 'biografia' },
  { label: 'Histórico', value: 'historico' },
  { label: 'Filme Noir', value: 'filme_noir' }
];


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
    codigoSeguranca: '',
    nomePerfil: ''
  });
  
  const [selectedGenders,setSelectedGenders] = useState<string[]>([]);
  
  const [vals, setVals] = useState({
    val1: '',
    val2: '',
    val3: ''
  });

 
  const [currentForm, setCurrentForm] = useState(1);

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

 
  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleCheckbox:CheckboxProps['onChange'] = (e) => {
    const { value, checked } = e.target;
    setSelectedGenders((prevValues) => {
      if (checked) {
        return [...prevValues, value];
      } 
      return prevValues.filter((item) => item !== value);
      
    });
  };

 
  const handleCurrentForm = (n:number) => {
    setCurrentForm(n);
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
        nome: formData.nomePerfil,
        iconUrl: "https://example.com/images/icon1.png",
        perfilInfantil: false,
        generosPreferidos: selectedGenders
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
        setCurrentForm(1); 
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
      
        {currentForm == 1 && (
          <div className="w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
            <ArrowLeftOutlined onClick={() => navigate('/login')} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
            <h2 className="text-3xl">Criar conta</h2>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor='email' className=" ml-2">Email</label>
                <input type="text" name="email" id="email" onChange={handleInput} className=" h-10 rounded-lg text-black p-2" value={formData.email} />
                {errors.email && <p className="text-red-500">{errors.email}</p>}
            </div>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor="nome" className=" ml-2">Nome de usuário</label>
                <input type="text" name="nome" id="nome" onChange={handleInput} className=" h-10 rounded-lg text-black p-2" value={formData.nome}/>
                {errors.nome && <p className="text-red-500">{errors.nome}</p>}
            </div>
            <div className=" w-11/12 flex flex-col">
                <label htmlFor="senha" className=" ml-2">Senha</label>
                <input type="password" name="senha" id="senha" onChange={handleInput} className=" h-10 rounded-lg text-black p-2" value={formData.senha}/>
                {errors.senha && <p className="text-red-500">{errors.senha}</p>}
            </div>
            <button onClick={() => handleCurrentForm(2)} type="button" className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
          </div>
        )}
        {currentForm == 2 && (
          <div className="w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
            <ArrowLeftOutlined onClick={() => handleCurrentForm(1)} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
            <h2 className="text-3xl">Forma de pagamento</h2>
            {errors.pagamento && <p className="text-red-500">{errors.pagamento}</p>}
            <div className="w-full flex flex-col justify-center gap-4">
              <div className="flex flex-row gap-1">
                <input type="radio" name="metodoPagamento" value="Credit Card" onChange={handleInput}  />
                <label>Cartão de crédito</label>
              </div>
              <div className="w-full flex flex-row flex-wrap items-center gap-5">
                <div className="w-3/5 flex flex-col">
                  <label>Número do cartão</label>
                  <input type="text" name="agencia" value={formData.agencia} onChange={handleInput} className="h-10 rounded-lg text-black p-2"  />
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
                    <label>Nome Titular</label>
                    <input type="text" name="numeroConta" value={formData.numeroConta} onChange={handleInput} className="h-10 rounded-lg text-black p-2" />
                  </div>
                  <div className="w-2/6 flex flex-col">
                    <label>Código de seg.</label>
                    <input type="text" value={vals.val3} onChange={handleVal} name="codigoSeguranca" id="val3" maxLength={3} className="h-10 rounded-lg text-black p-2" />
                  </div>
                </div>
              </div>
              <div className="flex flex-col">
                <div className="flex flex-row gap-1">
                  <input type="radio" name="metodoPagamento" value="ticket" onChange={handleInput} />
                  <label>Boleto</label>
                </div>
                <p className="ml-2 text-sm">*Será enviado o pdf do boleto para o email cadastrado</p>
              </div>
            </div>
            <button onClick={() => handleCurrentForm(3)} type="button" className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
          </div>
        )}
        {currentForm == 3 && (
          <div className="w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
              <ArrowLeftOutlined onClick={() => handleCurrentForm(2)} className="absolute scale-125 left-5 top-5 hover:cursor-pointer transition-transform transform hover:scale-150 " />
              <h2 className="text-3xl">Criar perfil</h2>
              <div className='flex flex-row gap-1'>
                <div className='w-full flex flex-col gap-1'>
                  <label htmlFor="nomePerfil">Nome do perfil</label>
                  <input type="text" name="nomePerfil" id="nomePerfil" onChange={handleInput} className="h-10 rounded-lg text-black p-2" value={formData.nomePerfil}  />
                </div>
               
              </div>
              
              <div className='w-full flex flex-col flex-wrap gap-1'>
                <h3 className='self-center'>Gêneros preferidos</h3>
                <div className='grid grid-cols-6 gap-1'>
                {genders.map((gender) =>(
                  <Checkbox 
                  key={gender.value}
                  value={gender.value}
                  name = {gender.value}
                  checked={selectedGenders.includes(gender.value)}
                  onChange={handleCheckbox}
                  className='custom-checkbox text-white col-span-2 items-center'
                  >{gender.label}</Checkbox>
                ))}

                </div>
              </div>
              <button onClick={handleSubmit} type="button" className="w-11/12 mt-10 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Criar conta</button>
          </div>
        )}
    </div>
  );
};

export default Register;
