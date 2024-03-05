import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";

const CurrentTemp = () => {
    // Return JSX
    return (
        <div className="info-box">
            <h2>Temperature for Odense and CPH</h2>
            <div className="box-content">
                <br/>
                <p><strong>OdenseTemp:</strong> </p>
                <br/>
                <p><strong>CPHTemp:</strong> </p>
            </div>
        </div>
    );
};

export default CurrentTemp;