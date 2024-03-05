import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";

const CurrentTemp = () => {
    // Return JSX
    return (
        <div className="info-box">
            <h2>Temperature Comparison (Odense/CPH)</h2>
            <div className="box-content">
                <br/>
                <p><strong>Current difference:</strong> </p>
                <br/>
                <p><strong>Average difference:</strong> </p>
            </div>
        </div>
    );
};

export default CurrentTemp;