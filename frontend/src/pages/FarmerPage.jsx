import React from 'react';
import FarmerFormComp from '../components/FarmerFormComp';
import FarmerListComp from '../components/FarmerListComp';
import '../styles/FormStyles.css'

const FarmerPage = () => {
    return (
        <div>
            <h1>Farmers</h1>
            <FarmerFormComp />
            <FarmerListComp />
        </div>
    );
};

export default FarmerPage;
