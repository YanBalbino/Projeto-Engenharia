import React from 'react';
import { SearchOutlined, MenuOutlined, UserOutlined, PoweroffOutlined, SettingOutlined } from "@ant-design/icons";
import { Image } from 'antd';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';


interface NavbarProps {
    navbarSelect: number;
    onNavbarSelect: (n: number) => void;
  }

  const Navbar: React.FC<NavbarProps> = ({ navbarSelect, onNavbarSelect }) => {

    const [accountModal, setAccountModal] = useState(false);
    const handleAccountModal = () => { 
      setAccountModal(!accountModal);
    }
    const navigate = useNavigate();

    const handleLogout = () =>{
      localStorage.removeItem('token');
      localStorage.removeItem('idUser');
      localStorage.removeItem('currentProfile');
      navigate('../../login')
    }

  return (
    <div className="mt-5 ml-5 flex flex-row justify-between">
      <ul className="flex flex-row gap-5 text-xl">
        {['StreamIt!', 'Filmes', 'Séries'].map((item, index) => (
          <li
            key={index}
            className={`relative text-2xl hover:cursor-pointer ${
              navbarSelect === index + 1 ? 'underline decoration-1 underline-offset-4' : ''
            }`}
            onClick={() => onNavbarSelect(index + 1)}
          >
            {item}
          </li>
        ))}
      </ul>
      <ul className="mr-5 flex flex-row gap-10 text-xl">

        {/* ícone de busca */}
        <SearchOutlined
          className={`w-8 h-8 p-1 rounded-lg hover:cursor-pointer hover:bg-cyan-800 ${
            navbarSelect === 4 ? 'bg-cyan-800' : ''
          }`}
          onClick={() => onNavbarSelect(4)}
        />

        {/* ícone de menu */}
        <MenuOutlined
          className={`w-8 h-8 p-1 rounded-lg hover:cursor-pointer hover:bg-cyan-800 ${
            navbarSelect === 5 ? 'bg-cyan-800' : ''
          }`}
          onClick={() => onNavbarSelect(5)}
        />

        {/* ícone de watchlist */}
        <Image
          width="32px"
          height="32px"
          src={`${process.env.PUBLIC_URL}/watchlistIcon.png`}
          alt="a"
          preview={false}
          className={`w-8 h-8 p-1 rounded-lg hover:cursor-pointer hover:bg-cyan-800 ${
            navbarSelect === 6 ? 'bg-cyan-800' : ''
          }`}
          onClick={() => onNavbarSelect(6)}
        />

        {/* ícone de perfil */}
        <UserOutlined
          className={`w-8 h-8 p-1 rounded-lg hover:cursor-pointer hover:bg-cyan-800 ${
            navbarSelect === 7 ? 'bg-cyan-800' : ''
          }`}
          onClick={() => handleAccountModal()}
        />

        {/* modal de vários menus */}
        <li>
          {accountModal && (
              <div>
                <div className="absolute top-0 right-0 bottom-0 left-0" onClick={() => handleAccountModal()}>
                  <div className="absolute top-16 right-20 bg-gray-800 bg-opacity-30 rounded-lg flex flex-col p-3 gap-2 z-20">
                    <div className="flex flex-row gap-2 hover:cursor-pointer" onClick={() => onNavbarSelect(7)}>
                      <UserOutlined></UserOutlined>
                      <p className="text-sm">Perfis</p>
                    </div>
                    
                    <hr className="border-gray-700"></hr>

                    <div className="flex flex-row gap-2 hover:cursor-pointer" onClick={() => onNavbarSelect(8)}>
                      <SettingOutlined />
                      <p className="text-sm">Configurações</p>
                    </div>
                    
                    <hr className="border-gray-700"></hr>

                    <div className="flex flex-row gap-2 hover:cursor-pointer" onClick={handleLogout}>
                      <PoweroffOutlined />
                      <p className="text-sm">Logout</p>
                    </div>
                    
                  </div>
                </div>
                
              </div>
            )}
        </li>
      </ul>
    </div>
  );
};

export default Navbar;
