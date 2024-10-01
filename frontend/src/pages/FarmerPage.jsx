import React, {useEffect, useState} from 'react';
import FarmerFormComp from '../components/FarmerFormComp';
import FarmerListComp from '../components/FarmerListComp';
import '../styles/FormStyles.css'
import axios from "axios";

const FarmerPage = () => {
    const [farmers, setFarmers] = useState([]);
    const [error, setError] = useState(null);

    const fetchFarmers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/farmers');
            setFarmers(response.data);
            setError(null);
        } catch (error) {
            console.error('Error fetching farmers', error);
            setError('Failed to load farmers.');
        }
    };

    useEffect(() => {
        fetchFarmers(); // Fetch farmers when the page loads
    }, []);

    const refreshFarmers = (newFarmer) => {
        setFarmers((prevFarmers) => [...prevFarmers, newFarmer]);
    };

    return (
        <div>
            <h1>Farmers</h1>
            <FarmerFormComp onAddFarmer={refreshFarmers} />
            {error ? (
                <p>{error}</p>
            ) : (
                <FarmerListComp farmers={farmers} />
            )}
        </div>
    );
};

export default FarmerPage;
