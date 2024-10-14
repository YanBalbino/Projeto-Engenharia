import './App.css';
import Login from './pages/login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/register';
import Catalog from './pages/catalog';
import Config from './pages/config';
import ConfigProfile from './pages/config/profile';
import ConfigPayment from './pages/config/payment';

function App() {
  return (
    <Router> 
      <Routes>
        {/*<Route path = '/' element={<Login/>}></Route>*/}
        <Route path='/login' element={<Login />}></Route>
        <Route path ='/cadastro' element={<Register />}> </Route>
        <Route path = '/catalogo' element={<Catalog/>}></Route>
        <Route path = '/config' element={<Config/>}></Route>
        <Route path = '/config/profile' element={<ConfigProfile/>}></Route>
        <Route path = '/config/payment' element={<ConfigPayment/>}></Route>
      </Routes>
    </Router>
  );
}

export default App;
