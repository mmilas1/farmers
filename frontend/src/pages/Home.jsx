import React from 'react';
import '../styles/FormStyles.css';
import logo from '../images/logo.png';

const Home = () => {
    return (
        <div className="container">
            <img src={logo} alt="Logo" className="logo" />
            <h1 className="welcome-text">Welcome to the Compensation Management System</h1>
        </div>
    );
};

export default Home;
