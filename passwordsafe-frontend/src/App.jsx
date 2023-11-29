
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from './components/loginPage';
import PasswordSafe from './components/passwordSafe';
import { useState, useEffect } from 'react';


function App() {

  const storedToken = JSON.parse(sessionStorage.getItem('token'));
  const [userToken, setUserToken] = useState(storedToken);

  useEffect(() => {
    if (storedToken) {
      console.log('User is logged in. Token:', storedToken);
    }
  }, [storedToken]);

  const handleLogin = (token) => {
    setUserToken(token);
    sessionStorage.setItem('token', JSON.stringify(token));
  }

  return (
    <BrowserRouter>
      <Routes>
        {!userToken ? (
          <Route path='/*' element={<LoginPage setUserToken={handleLogin} />
        } />
        ) : (
          <>
            <Route path='/' element={<PasswordSafe />} />
          
          </>
        )}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
