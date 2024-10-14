import './App.css';
import Login from './pages/login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/register';
import Catalog from './pages/catalog';
import Player from './pages/player';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/login' element={<Login />}></Route>
        <Route path ='/cadastro' element={<Register />}> </Route>
        <Route path = '/catalogo' element={<Catalog/>}></Route>
  
        <Route path='/player' element={<Player/>} />
      </Routes>
    </Router>
  );
}

export default App;
