import React, { useState, useEffect, useRef } from 'react';
import './MainSite.css';
import CurrentTemp from "./components/CurrentTemp";
import AverageTemp from "./components/AverageTemp";
import TempCompare from "./components/TempCompare";
const MainSite = () => {

    // Return JSX
    return (
        <div className="container">
            <div className="header">
                <h1>API Weather Checker</h1>
                <br/>
            </div>
            <div className="dashboard">

                {/* CurrenTemp */}
                <CurrentTemp />

                {/* AverageTemp */}
                <AverageTemp />

                {/* TempCompare */}
                <TempCompare />


            </div>
        </div>
    );
};

export default MainSite;