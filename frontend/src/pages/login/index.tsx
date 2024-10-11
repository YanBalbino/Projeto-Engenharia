import { useNavigate } from "react-router-dom"
import React, { useState} from 'react';
import { Checkbox } from "antd";
import '../register/checkbox.css'

type ReturnUserDTO = {
    id: number;
    senha: string;
    email: string;
    
};

const Login = () => {

    const [errors, setErrors] = useState({
       login: '',
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
        try{
            const response = await fetch('http://localhost:8080/api/login',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                  },
                body: JSON.stringify(data),
            });
            if(!response.ok){
                setErrors({login:"email ou senha incorretas"})
            }
            else{
                navigateTo('/catalogo')
            }
            

        }
        catch (error) {
            console.error('Erro ao entrar:', error);
        }
    }
   
    const navigateTo = (rota:string) =>{
        
        navigate(rota)
    }
    return (
       <div className="text-white font-inter  w-screen h-screen bg-gradient-to-b from-black to-cyan-950 flex flex-col pt-5 items-center gap-4 ">
            <h1 className=" text-6xl">StreamIt!</h1>
            <div className=" w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
                {errors.login && <p className="text-red-500">{errors.login}</p>}
                <h2 className="text-3xl">Entrar</h2>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="email" className=" ml-2">Email</label>
                    <input type="text" name="email" id="email" onChange={handleInput} className=" h-10 rounded-lg text-black p-2 focus:outline-none focus:ring-2 focus:ring-cyan-600"/>
                </div>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="email" className=" ml-2">Senha</label>
                    <input type="password" name="senha" id="senha" onChange={handleInput} className="h-10 rounded-lg text-black p-2  focus:outline-none focus:ring-2 focus:ring-cyan-600"/>
                    <a href="" className="text-blue-500 hover:text-blue-700 text-sm">Esqueceu a senha?</a>
                </div>
                <button onClick={ handleSubmit} className="w-11/12 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
                <div className="flex flex-row gap-3 mr-40">
                    <Checkbox 
                    className='custom-checkbox text-white col-span-2 items-center'
                    >Mantenha-me conectado</Checkbox>
                </div>
            </div>
            <div className=" w-1/3 flex flex-row gap-1 justify-center" >
                <div className=" mt-3 w-1/3 border-t border-white"></div>
                <span className=" text-sm">Novo por aqui ?</span>
                <div className="mt-3 w-1/3 border-t border-white"></div>
            </div>
            <div className=" w-1/3 flex flex-col items-center gap-2">
                <button onClick={() => navigateTo('/cadastro')} className=" w-2/3 h-10 text-black rounded-lg bg-white    hover:bg-cyan-600">Crie a sua conta</button>
                <span>Ou</span>
                <button className="w-2/3 h-10 text-black rounded-lg bg-white hover:bg-cyan-600">Importar dados</button>
            </div>
       </div>
    )
}

export default Login