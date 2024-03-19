// AverageTemperatureCard.js
import React from 'react';

const AverageTemperatureCard = ({ location, temperature }) => (
    <div className="info-box">
        <h2>Average temperature for {location} (Last 7 days)</h2>
        <div className="box-content">
            <br/>
            <p><strong>{location} Temp Average: </strong>{temperature !== null ? `${temperature} °C` : 'Loading...'}</p>
        </div>
    </div>
);

export default AverageTemperatureCard;
