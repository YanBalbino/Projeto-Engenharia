import { useNavigate } from "react-router-dom";
import  background  from "../../../src/images/imagem_bg_landingPage.jpg";

const LandingPage = () => {

    const navigate = useNavigate();

    return (
        <div className="text-white font-inter w-screen h-screen flex flex-col gap-4 overflow-x-hidden m-0">
            <img 
                src={background} 
                alt="Imagem de fundo" 
                className="absolute z-[-1] w-screen h-screen brightness-50 saturate-50"/>
            <header className="flex flex-row justify-between px-20 py-4">
                <h1 className="text-4xl">Stream it!</h1>
                <button 
                    type="button" 
                    className=" bg-cyan-600 py-1 px-3 rounded-md hover:bg-cyan-400" 
                    onClick={() => navigate('/login')}>Entrar
                </button>
            </header>
            <div className="flex flex-col rounded-2xl overflow-hidden shadow-2xl 
            bg-slate-400 bg-opacity-10 h-3/4 self-center items-center justify-center backdrop-blur">
                <div className="max-w-[600px]">  
                    <p className="relative text-4xl text-center ">Filmes e séries incríveis,  sem limites, em um só lugar!</p>
                </div>
              
                <div className="mt-7 flex flex-col items-center">
                    <label className="relative text-2xl mb-8">Apenas R$4,44. Cancele quando quiser.</label>
                    <label className="relative text-base">Quer assistir? Experimente 30 dias grátis!</label>
                    <button 
                        type="button" 
                        className="relative bg-cyan-600 mt-10 py-3 px-20 rounded-md hover:bg-cyan-400"
                        onClick={() => navigate('/cadastro')}>Cadastrar-se</button>
                </div>
            </div>
            
            
        </div>
    );
}
export default LandingPage;