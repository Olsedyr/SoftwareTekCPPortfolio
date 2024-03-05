import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CurrentTemp = () => {
    const [odenseTemp, setOdenseTemp] = useState(null);
    const [cphTemp, setCphTemp] = useState(null);

    const fetchData = async () => {
        try {
            // Fetch temperature data from backend API
            const responseOdense = await axios.get("api/weather/odense/latest");
            const responseKøbenhavn = await axios.get("api/weather/københavn/latest");

            // Extract temperature values from response data
            const { weatherdata : odenseTemperature } = responseOdense.data;
            const { weatherdata : cphTemperature } = responseKøbenhavn.data;

            // Update state with temperature values
            setOdenseTemp(odenseTemperature);
            setCphTemp(cphTemperature);
        } catch (error) {
            console.error('Error fetching temperature data:', error);
        }
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchData();
        }, 5000); // Fetch data every 5 seconds

        // Clear the interval when the component unmounts
        return () => clearInterval(intervalId);
    }, []); // Empty dependency array to run effect only once on component mount

    return (
        <div className="info-box">
            <h2>Temperature for Odense and Copenhagen</h2>
            <div className="box-content">
                <br/>
                <p><strong>OdenseTemp:</strong> {odenseTemp !== null ? `${odenseTemp} °C` : 'Loading...'}</p>
                <br/>
                <p><strong>CPHTemp:</strong> {cphTemp !== null ? `${cphTemp} °C` : 'Loading...'}</p>
            </div>
        </div>
    );
};

export default CurrentTemp;
