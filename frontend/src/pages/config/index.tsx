import TabMenu from "./tabMenu";
import { useNavigate } from 'react-router-dom'

const Config = () => {

    const navigate = useNavigate();

    return(
        <div>
            <div className="text-left pl-16">
                <h1 className="text-4xl">Conta e configurações</h1>
                <TabMenu/>
            </div>
        </div>
    );
}

export default Config;