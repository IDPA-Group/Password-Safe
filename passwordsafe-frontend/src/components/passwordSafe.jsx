import React, { useState } from 'react';
import LoginPage from './loginPage';
import { Link } from 'react-router-dom';



function PasswordSafe() {
    const history = useHistory();
  const [blocks, setBlocks] = useState([
    
  ]);

  const handleAddBlock = () => {
    const newBlock = {
      title: document.getElementById('titleInput').value,
      username: document.getElementById('usernameInput').value,
      password: document.getElementById('passwordInput').value,
    };

    setBlocks([...blocks, newBlock]);   

    document.getElementById('titleInput').value = '';
    document.getElementById('usernameInput').value = '';
    document.getElementById('passwordInput').value = '';
  };

  const handleLogout = () => {
  

  };

  const listItems = blocks.map((block, index) => (
    <li key={index}>
      Plattform: {block.title}, Username: {block.username}, Password: {block.password}
    </li>
  )
  
  );

  return (
    <>
 < className="LogoutButton" onClick={handleLogout}>
        Log Out
      <Link ></Link>
      <ul>{listItems}</ul>
      <div className="Block">
        <p>
          Platform <input type="text" id="titleInput" />
        </p>
        <p>
          Username <input type="text" id="usernameInput" />
        </p>
        <p>
          Password <input type="text" id="passwordInput" />
        </p>
      </div>
      <button className="AddButton" onClick={handleAddBlock}>
        Add
      </button>
    </>
  );
}

export default PasswordSafe;