import passwordSafe from "./passwordSafe"
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';



function LoginPage(){

    return(
        <>
        <h1>Log in</h1>
        <form onSubmit={handleSubmit}>
            <label>
                <p>Username</p>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            </label>
            <label>
                <p>Password</p>
                <input type="password" value={password} autoComplete="new-password" onChange={(e) => setPassword(e.target.value)} />
            </label>
            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
        </>
    )
}
export default LoginPage;