
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from './components/loginPage';

function App() {

  return (
    <>
    <BrowserRouter>
            <Routes>
              <Route path="/" element={<LoginPage/>}>
                <Route/>
               

              </Route>
            </Routes>
          </BrowserRouter>
      
    </>
  )
}

export default App
