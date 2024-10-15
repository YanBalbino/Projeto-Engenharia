import TabMenu from "./tabMenu";
import { useNavigate } from 'react-router-dom'

const Config = () => {

    const navigate = useNavigate();

    return(
        <div className="w-screen h-screen bg-gradient-to-b from-[#000713] to-[#2C4167] text-white flex flex-col flex-wrap">
            <h1 
                className="self-start justify-self-start pl-16 pt-5 text-5xl hover:cursor-pointer" 
                onClick={() => navigate('/catalogo')}>Stream it!
            </h1>
            <div className="text-left pt-10 pl-16">
                <h1 className="text-3xl">Conta e configurações</h1>
                <TabMenu/>
            </div>
        </div>
    );
}

export default Config;