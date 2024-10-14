import React, { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ConfigProfile = () => {
    const [userInfo, setUserInfo] = useState([
        { name: 'Nome', value: 'Yan Balbino', disabled: true},
        { name: 'Email', value: 'email@gmail.com', disabled: true},
        { name: 'Senha', value: '1234567', disabled: true},
        { name: 'Número de celular', value: '+5584999999999', disabled: true}
    ]);

    const [inputClassName, setInputClassName] = useState('text-gray-400 bg-transparent');

    const handleInputEdit = (dis: Boolean) => {
        if (!dis) {
            setInputClassName('text-black bg-white');
        }
        else {
            setInputClassName('text-gray-400 bg-transparent');
        }
    }

    const editField = (index: number) => {
        const newUserInfo = [...userInfo];
        newUserInfo[index].disabled = !newUserInfo[index].disabled;
        setUserInfo(newUserInfo);
        handleInputEdit(newUserInfo[index].disabled);
    }

    const handleInputChange = (index: number, event: ChangeEvent<HTMLInputElement>) => {
        const newUserInfo = [...userInfo];
        newUserInfo[index].value = event.target.value;
        setUserInfo(newUserInfo);
    }

    const optionGenerator = () => {
        return userInfo.map((option, index) => {
            const currentClassName = option.disabled ? 'rounded-md text-gray-400 bg-transparent' : 'rounded-md text-black bg-white';
            return (
                <div key={index} className="grid grid-cols-2 gap-10 px-3 py-3">
                    <div className="flex flex-col">
                        <label className="font-bold">{option.name}:</label>
                        <input 
                            type={option.name === 'Senha' ? 'password' : 'text'} 
                            value={option.value} 
                            disabled={option.disabled}
                            className={currentClassName}  
                            onChange={(event) => handleInputChange(index, event)}/>
                    </div>
                    <div className="flex flex-col items-center">
                        <button type="button" className="relative bg-gray-600 my-2 px-10 py-1 rounded-md" 
                            onClick={() => editField(index)}>{option.disabled ? 'Editar' : 'Salvar'}
                        </button>
                    </div>
                </div>
            )
        })
    }

    const navigate = useNavigate();
    
    const handleSubmit = () => {
        navigate('/config');
    }

    return (
        <div className="w-screen h-screen bg-gradient-to-b from-black to-cyan-950 text-white flex flex-col items-center flex-wrap">
            <h1 className="relative mt-12 text-5xl">Detalhes da conta</h1>
            <div className="rounded-xl bg-gray-800 w-2/5 mt-10">
                {optionGenerator()}
            </div>
            <button type="submit" className="relative bg-cyan-600 my-4 px-10 py-1 rounded-md" onClick={() => handleSubmit()}>Concluir</button>
        </div>
    );
    }
export default ConfigProfile;