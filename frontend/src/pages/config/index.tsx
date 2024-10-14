import TabMenu from "./tabMenu";


const Config = () => {
    return(
        <div className="w-screen h-screen bg-gradient-to-b from-black to-cyan-950 text-white flex flex-col flex-wrap gap-20">
            <div className="text-left mt-20 ml-20">
                <h1 className="text-3xl">Conta e configurações</h1>
                <TabMenu/>
            </div>
        </div>
    );
}

export default Config;