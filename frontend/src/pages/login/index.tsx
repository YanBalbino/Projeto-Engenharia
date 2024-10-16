import { useNavigate } from "react-router-dom"
import React, { useState} from 'react';
import { Checkbox } from "antd";
import '../register/checkbox.css'
import  loginService  from '../../services/loginService'

type ReturnUserDTO = {
    id: number;
    senha: string;
    email: string;
    
};

const Login = () => {

    const [errors, setErrors] = useState({
       senha: '',
       email: '',
      });
    const [formData, setFormData] = useState({
       email: '',
       senha: ''
      });

    const navigate = useNavigate()

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFormData(prevData => ({
          ...prevData,
          [name]: value
        }));
      };

    const handleSubmit = async() => {
        const data = {
            senha: formData.senha,
            email: formData.email
            
        }
     
        const response = await loginService(data)

        if(response){
            if(!response.ok){
                const errorData = await response.json()
                setErrors(prevErrors => ({
                    ...prevErrors,
                    senha: errorData.errors.find((msg: string) => msg.toLowerCase().includes('senha')) || '',
                    email: errorData.errors.find((msg: string) => msg.toLowerCase().includes('email')) || '',
                }));
            }
            else{
                const responseText = await response.text(); 
                const data = JSON.parse(responseText);
                const token = data.token;
                const idUser = data.idUser;
                localStorage.setItem('token', token); 
                localStorage.setItem('idUser', idUser);
                navigate('/catalog');
               
            }
        }
    }

   
    const navigateTo = (rota:string) =>{
        
        navigate(rota)
    }
    return (
       <div className="text-white font-inter  w-screen h-screen bg-gradient-to-b from-[#000713] to-[#2C4167] flex flex-col pt-5 items-center gap-6 ">
            <h1 className=" text-4xl font-bold">StreamIt!</h1>
            <div className=" w-1/3 flex flex-col border border-gray-500 rounded-xl bg-zinc-700 bg-opacity-40 p-7 gap-5 items-center">
                
                <h2 className="text-3xl">Entrar</h2>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="email" className=" ml-2">Email</label>
                    <input 
                        type="text" 
                        name="email" 
                        id="email" 
                        onChange={handleInput} 
                        className=" h-9 rounded-lg text-black p-2 focus:outline-none focus:ring-2 focus:ring-cyan-600"/>

                    {errors.email && <p className="text-red-500">{errors.email}</p>}

                </div>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="email" className=" ml-2">Senha</label>
                    <input 
                        type="password" 
                        name="senha" 
                        id="senha" 
                        onChange={handleInput} 
                        onKeyUp={(e) => e.key === 'Enter' && handleSubmit()}
                        className="h-9 rounded-lg text-black p-2  focus:outline-none focus:ring-2 focus:ring-cyan-600"/>

                    {errors.senha && <p className="text-red-500">{errors.senha}</p>}
                    
                    <a href="" className="text-blue-500 hover:text-blue-700 text-sm">Esqueceu a senha?</a>
                </div>
                <button onClick={ handleSubmit} className="w-11/12 h-10 rounded-lg bg-[#216EAD] hover:bg-sky-600">Continuar</button>
                <div className="flex flex-row gap-3 self-start pl-4">
                    <Checkbox 
                    className='custom-checkbox text-white col-span-2 items-center'
                    >Mantenha-me conectado</Checkbox>
                </div>
            </div>
            <div className=" w-1/3 flex flex-row gap-1 justify-center" >
                <div className=" mt-3 w-1/3 border-t border-gray-500"></div>
                <span className=" text-sm text-gray-500">Novo por aqui ?</span>
                <div className="mt-3 w-1/3 border-t border-gray-500"></div>
            </div>
            <div className=" w-1/3 flex flex-col items-center gap-2">
                <button onClick={() => navigateTo('/cadastro')} className=" w-2/3 h-10 text-black rounded-lg bg-gray-300 hover:bg-sky-700 hover:text-white">Crie a sua conta</button>
                <span>Ou</span>
                <button className="w-2/3 h-10 text-black rounded-lg bg-gray-300 hover:bg-sky-700 hover:text-white">Importar dados</button>
            </div>
       </div>
    )
}

export default Login