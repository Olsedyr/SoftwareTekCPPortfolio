// TemperatureComparisonCard.js
import React from 'react';

const TemperatureComparisonCard = ({ title, currentDifference, averageDifference }) => (
    <div className="info-box">
        <h2>{title}</h2>
        <div className="box-content">
            <br/>
            <p><strong>Current difference: </strong>{currentDifference !== null ? `${currentDifference} °C` : 'Loading...'} </p>
            <br/>
            <p><strong>Average difference: </strong>{averageDifference !== null ? `${averageDifference} °C` : 'Loading...'} </p>
        </div>
    </div>
);

export default TemperatureComparisonCard;
