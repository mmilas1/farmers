import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // For navigation after successful login
import '../styles/FormStyles.css'

const LoginFormComp = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                username: email, 
                password: password
            });
            
            if (response.data === "Login successful!") {
                alert("Login successful");
                navigate("/petitions"); // Redirect to petitions page
            } else {
                alert("Invalid credentials");
            }
        } catch (error) {
            console.error("Error logging in", error);
            alert("Login failed");
        } finally {
            // Clear input fields after login attempt
            setEmail('');
            setPassword('');
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <label>
                    Email:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </label>
                <br />
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginFormComp;
