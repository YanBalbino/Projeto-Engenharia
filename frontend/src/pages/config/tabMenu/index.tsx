import { Tab, TabGroup, TabList, TabPanel, TabPanels } from '@headlessui/react'
import React from 'react'
import { useNavigate } from 'react-router-dom'

const configList = [
  {
    name: 'Sua conta',
    options: [
      {
        name: 'Seus detalhes',
        desc: 'Altere seu nome, email, senha e outras informações pessoais',
        
      },
      {
        name: 'Pagamento',
        desc: 'Altere métodos de pagamento',
      },
      {
        name: 'Idioma de exibição',
        desc: 'Escolha o idioma de exibição do aplicativo',
      }
    ]
  },
  {
    name: 'Reprodutor de vídeo',
    options: [
      {
        name: 'Legenda',
        desc: 'Escolha a legenda padrão',
      },
      {
        name: 'Áudio',
        desc: 'Escolha a qualidade de áudio padrão',
      }
    ]
  },
  {
    name: 'Histórico',
    desc: 'Veja o histórico de vídeos assistidos'
  }
];

const TabMenu = () => {
  const navigate = useNavigate();

  const configNames = configList.map(category => 
    <Tab className="px-2.5 py-1.5 rounded-full data-[hover]:bg-neutral-800 
      data-[selected]:bg-neutral-700 mr-3">{category.name}
    </Tab>);

  const configOps = configList.map(category => 
    <TabPanel>
      <div className="flex flex-col gap-2">
          {category.options ? category.options.map(op => 
            <div className="flex flex-col mt-2 gap-1 hover:underline hover:cursor-pointer" onClick={() => handleClick(op.name)}>
              <h2 className="text-lg">{op.name}</h2>
              <p className="text-xs">{op.desc}</p>
              <hr className="border-slate-600 mt-3"></hr>
            </div> ): ( <p className="text-sm">Nenhuma configuração disponível</p>)
          }
      </div>
    </TabPanel>
  );

  //TODO combobox do idioma e seletor nas legendas

  const handleClick = (name: string) => {
    if(name === 'Seus detalhes') {
      navigate('/config/profile');
    }
    if(name === 'Pagamento') {
      navigate('/config/payment');
    }
  };

  return (
    <div className="relative mt-8">
        <TabGroup defaultIndex={0}>
            <TabList>
                {configNames}
            </TabList>
            <TabPanels className="rounded-xl mt-3 bg-gray-700 opacity-90 px-3 pt-2 pb-4 mr-20">
                {configOps}
            </TabPanels>
        </TabGroup>
    </div>
  );
};

export default TabMenu;