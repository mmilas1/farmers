import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/FormStyles.css'
const Navbar = () => {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/farmers">Farmers</Link>
                </li>
                <li>
                    <Link to="/login">Login</Link>
                </li>
                <li>
                    <Link to="/petitions">Petitions</Link>
                </li>
                <li>
                    <Link to="/inspectors">Inspectors</Link> 
                </li>
            </ul>
        </nav>
    );
};

export default Navbar;
