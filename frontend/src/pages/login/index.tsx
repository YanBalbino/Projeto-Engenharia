import { useNavigate } from "react-router-dom"



const Login = () => {

    const navigate = useNavigate()
    const handleClick = (rota:string) =>{
   
        navigate(rota)
    }
    return (
       <div className="text-white font-inter  w-screen h-screen bg-gradient-to-b from-black to-cyan-950 flex flex-col pt-5 items-center  gap-4 ">
            <h1 className=" text-6xl">StreamIt!</h1>
            <div className=" w-1/3 flex flex-col border-2 border-white rounded-xl bg-black p-10 gap-5 items-center">
                <h2 className="text-3xl">Entrar</h2>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="" className=" ml-2">Email</label>
                    <input type="text" name="" id="" className=" h-10 rounded-lg text-black p-2 focus:outline-none focus:ring-2 focus:ring-cyan-600"/>
                </div>
                <div className=" w-11/12 flex flex-col">
                    <label htmlFor="" className=" ml-2">Senha</label>
                    <input type="password" name="" id="" className="h-10 rounded-lg text-black p-2  focus:outline-none focus:ring-2 focus:ring-cyan-600"/>
                    <a href="" className="text-blue-500 hover:text-blue-700 text-sm">Esqueceu a senha?</a>
                </div>
                <button onClick={() => handleClick('/catalogo')} className="w-11/12 h-10 rounded-lg bg-cyan-600 hover:bg-cyan-400">Continuar</button>
                <div className="flex flex-row gap-3 mr-40">
                    <input type="checkbox" name="" id="" className="w-4   "/> 
                    <label htmlFor="">Mantenha-me conectado</label>
                </div>
            </div>
            <div className=" w-1/3 flex flex-row gap-1 justify-center" >
                <div className=" mt-3 w-1/3 border-t border-white"></div>
                <span className=" text-sm">Novo por aqui ?</span>
                <div className="mt-3 w-1/3 border-t border-white"></div>
            </div>
            <div className=" w-1/3 flex flex-col items-center gap-2">
                <button onClick={() => handleClick('/cadastro')} className=" w-2/3 h-10 text-black rounded-lg bg-white    hover:bg-cyan-600">Crie a sua conta</button>
                <span>Ou</span>
                <button className="w-2/3 h-10 text-black rounded-lg bg-white hover:bg-cyan-600">Importar dados</button>
            </div>
       </div>
    )
}

export default Login