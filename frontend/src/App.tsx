import './App.css';
import Login from './pages/login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/register';
import Catalog from './pages/catalog';
import Config from './pages/config';
import ConfigProfile from './pages/config/profile';
import ConfigPayment from './pages/config/payment';
import Player from './pages/player';
import LandingPage from './pages/landingPage';
import ProtectedRoute from './utils/protectedRoute';
import FilmRegister from './pages/mediaRegister/filmRegister';
import SeriesRegister from './pages/mediaRegister/seriesRegister';

function App() {
  return (
    <Router> 
      <Routes>
        <Route path = '/' element={<LandingPage/>}></Route>
        <Route path='/login' element={<Login />}></Route>
        <Route path ='/cadastro' element={<Register />}> </Route>
        <Route path="/catalog"element={<ProtectedRoute><Catalog/></ProtectedRoute>}/>
        <Route path="/config"element={<ProtectedRoute><Config/></ProtectedRoute>}/>
        <Route path="/config/profile"element={<ProtectedRoute><ConfigProfile/></ProtectedRoute>}/>
        <Route path="/config/payment"element={<ProtectedRoute><ConfigPayment/></ProtectedRoute>}/>
        <Route path="/player"element={<ProtectedRoute><Player/></ProtectedRoute>}/>
        <Route path="/filmRegister"element={<ProtectedRoute><FilmRegister/></ProtectedRoute>}/>
        <Route path="/seriesRegister"element={<ProtectedRoute><SeriesRegister/></ProtectedRoute>}/>
      </Routes>
    </Router>
  );
}

export default App;
