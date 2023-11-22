
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from './components/loginPage';
import PasswordSafe from './components/passwordSafe';
import { useState } from 'react';

function App() {

  const storedToken = JSON.parse(sessionStorage.getItem('token'));
  const [userToken, setUserToken] = useState(storedToken);

  useEffect(() => {
    if (storedToken) {
      console.log('User is logged in. Token:', storedToken);
    }
  }, []);

  const handleLogin = () => {
    setUserToken(token);
    sessionStorage.setItem('token', JSON.stringify(token));
  }

  return (
    <>
    {!userToken ? (
      <LoginPage setUserToken={handleLogin}/>
    ) : (
      <BrowserRouter>
            <Routes>
              <Route path='/' element={<LoginPage/>}/>
                <Route path='/safe' element={<PasswordSafe/>}/>
            </Routes>
          </BrowserRouter>
    )}
      
    </>
  )
}

export default App
