import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import FarmerPage from './pages/FarmerPage';
import PetitionPage from './pages/PetitionPage';
import LoginFormComp from './components/LoginFormComp';
import InspectorPetitionListComp from './components/InspectorPetitionListComp';  // Import the new petition list component

const App = () => {
    return (
        <Router>
            <Navbar />
            <div>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/farmers" element={<FarmerPage />} />
                    <Route path="/petitions" element={<PetitionPage />} />
                    <Route path="/login" element={<LoginFormComp />} />
                    <Route path="/inspectors/petitions" element={<InspectorPetitionListComp />} />  {/* Inspector Petition List */}
                </Routes>
            </div>
        </Router>
    );
};

export default App;