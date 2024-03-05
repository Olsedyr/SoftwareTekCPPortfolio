import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";

const CurrentTemp = () => {
    // Return JSX
    return (
        <div className="info-box">
            <h2>Average Temperature (Weekly)</h2>
            <div className="box-content">
                <br/>
                <p><strong>Odense Average:</strong> </p>
                <br/>
                <p><strong>CPH Average:</strong> </p>
            </div>
        </div>
    );
};

export default CurrentTemp;