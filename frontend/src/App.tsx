import './App.css';
import Login from './pages/login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Cadastro from './pages/register';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/login' element={<Login />}></Route>
        <Route path ='/cadastro' element={<Cadastro />}> </Route>
      </Routes>
    </Router>
  );
}

export default App;
