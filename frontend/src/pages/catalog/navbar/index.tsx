import React from 'react';
import { SearchOutlined, MenuOutlined, UserOutlined } from "@ant-design/icons";
import { Image } from 'antd';
import SearchBar from '../../search';

interface NavbarProps {
    navbarSelect: number;
    onNavbarSelect: (n: number) => void;
  }

  

  const Navbar: React.FC<NavbarProps> = ({ navbarSelect, onNavbarSelect }) => {
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
          onClick={() => onNavbarSelect(7)}
        />
      </ul>
    </div>
  );
};

export default Navbar;
