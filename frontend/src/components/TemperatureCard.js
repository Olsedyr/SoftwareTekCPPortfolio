// TemperatureCard.js
import React from 'react';

const TemperatureCard = ({ location, temperature, time }) => (
    <div className="info-box">
        <h2>Temperature for {location}</h2>
        <div className="box-content">
            <br/>
            <p>
                <strong>{location} Temp: </strong>
                {temperature !== null ? `${temperature} °C` : ''}
                <br/>
                {time !== null ? `${new Date(time).toLocaleString('en-DK', { hour12: false })} ` : ''}
            </p>
        </div>
    </div>
);

export default TemperatureCard;
