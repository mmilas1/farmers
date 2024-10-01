import React, { useEffect, useState } from 'react';
import '../styles/FormStyles.css';
import axios from "axios";

const FarmerListComp = () => {
    const [farmers, setFarmers] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchFarmers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/farmers');
                setFarmers(response.data);
            } catch (error) {
                console.error('Error fetching farmers', error);
                setError('Failed to load farmers.');
            }
        };
        fetchFarmers();
    }, []);

    return (
        <div>
            <h2>List of Farmers</h2>
            {error ? (
                <p>{error}</p>
            ) : farmers.length > 0 ? (
                <ul>
                    {farmers.map((farmer) => (
                        <li key={farmer.id}>
                            {farmer.name} ({farmer.email})
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No farmers found.</p>
            )}
        </div>
    );
};

export default FarmerListComp;
