
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from './components/loginPage';
import PasswordSafe from './components/passwordSafe';
import { useState } from 'react';

function App() {

 

  return (
    <>
    
      <BrowserRouter>
            <Routes>
              <Route path='/' element={<LoginPage/>}/>
                <Route path='/safe' element={<PasswordSafe/>}/>
            </Routes>
          </BrowserRouter>
  
      
    </>
  )
}

export default App
